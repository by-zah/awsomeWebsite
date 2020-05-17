package ua.khnu.reposetory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.exception.InitException;
import ua.khnu.util.DBName;
import ua.khnu.util.ThreeConsumer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

@Component
public abstract class AbstractRepository<T> {
    private static final Logger LOG = Logger.getLogger(UserRepository.class);
    public static final String CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS = "can not read column properties for this class";
    private static final String SEPARATOR = ";";
    protected JdbcTemplate jdbcAccessor;
    private Class<T> genClass;
    private Map<Class<?>, ThreeConsumer<Object, Field, String>> settersFromString;

    @Autowired
    public AbstractRepository(JdbcTemplate jdbcAccessor) {
        this.jdbcAccessor = jdbcAccessor;
        genClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        initDataFromStringSetters();
    }

    public Optional<Integer> queryForInt(String query, Object... args) {
        return Optional.ofNullable(jdbcAccessor.queryForObject(query, Integer.class, args));
    }

    protected List<T> getObjectListFromResultList(List<Map<String, Object>> resList) {
        return resList.stream().map(m -> {
            try {
                return gerObject(m, genClass);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                LOG.error(e);
                throw new InitException(CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS);
            }
        }).collect(Collectors.toList());
    }

    protected <E> E gerObject(Map<String, Object> map, Class<E> eClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        E obj = (E) getDefaultConstructor(eClass).newInstance();
        Arrays.stream(eClass.getDeclaredFields()).forEach(f -> {
            String fieldDBName = getDBName(f);
            try {
                f.setAccessible(true);
                if (f.getClass().isAnnotationPresent(DBName.class)) {
                    Object tmp = gerObject(map, f.getClass());
                    f.set(obj, tmp);
                } else if (f.getType().equals(List.class) || Arrays.asList(f.getType().getInterfaces()).contains(List.class)) {
                    Class<?> innerClass = (Class<?>)((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
                    f.set(obj, getInnerList(map, innerClass));
                } else {
                    f.set(obj, map.get(fieldDBName));
                }
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                LOG.error(e);
                throw new InitException(CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS,e);
            }
        });
        return obj;
    }

    private Constructor<?> getDefaultConstructor(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredConstructors())
                .filter(c -> {
                    c.setAccessible(true);
                    return c.getParameterCount() == 0;
                }).findAny().orElse(null);
    }

    private List<Object> getInnerList(Map<String, Object> map, Class<?> objClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object> res = new ArrayList<>();
        Map<Field, String[]> values = new HashMap<>();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String dbName = getDBName(field);
            values.put(field, getArrBySeparator(map.get(dbName)));
        }
        Constructor<?> constructor = getDefaultConstructor(objClass);
        String[] maxLengthArr = values.values().stream().max(Comparator.comparingInt(a -> a.length)).orElse(new String[0]);
        for (int i = 0; i < maxLengthArr.length; i++) {
            Object element = constructor.newInstance();
            for (Map.Entry<Field, String[]> entry : values.entrySet()) {
                Field f = entry.getKey();
                String[] v = entry.getValue();
                settersFromString.get(f.getType()).accept(element, f, getFromArrayIfExist(v, i));
            }
            res.add(element);
        }
        return res;
    }

    private String getDBName(Field f) {
        DBName[] annotations = f.getAnnotationsByType(DBName.class);
        return annotations.length == 0 ? f.getName() : annotations[0].name();
    }

    private String[] getArrBySeparator(Object s) {
        String str = String.valueOf(s);
        return str.split(SEPARATOR);
    }

    private String getFromArrayIfExist(String[] arr, int i) {
        return i < arr.length ? arr[i] : null;
    }

    private void initDataFromStringSetters() {
        settersFromString = new HashMap<>();
        settersFromString.put(String.class, (o, f, v) -> f.set(o, v));
        settersFromString.put(Double.class, (o, f, v) -> f.set(o, Double.parseDouble(v)));
        settersFromString.put(Integer.class, (o, f, v) -> f.set(o, Integer.parseInt(v)));
    }

}
