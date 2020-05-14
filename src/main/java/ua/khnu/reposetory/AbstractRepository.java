package ua.khnu.reposetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.exception.InitException;
import ua.khnu.util.DBName;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public abstract class AbstractRepository<T> {
    public static final String CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS = "can not read column properties for this class";
    protected JdbcTemplate jdbcAccessor;
    private Class<T> genClass;

    @Autowired
    public AbstractRepository(JdbcTemplate jdbcAccessor) {
        this.jdbcAccessor = jdbcAccessor;
        genClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Optional<Integer> queryForInt(String query, Object... args) {
        return Optional.ofNullable(jdbcAccessor.queryForObject(query, Integer.class, args));
    }

    protected List<T> getObjectListFromResultList(List<Map<String, Object>> resList) {
        return resList.stream().map(m -> {
            try {
                T obj = (T) gerObject(m, genClass);
                return obj;
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                throw new InitException(CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS);
            }
        }).collect(Collectors.toList());
    }

    protected Object gerObject(Map<String, Object> map, Class<?> innerClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object obj = getDefaultConstructor(innerClass).newInstance();
        Arrays.stream(innerClass.getDeclaredFields()).forEach(f -> {
            DBName[] annotations = f.getAnnotationsByType(DBName.class);
            String fieldDBName = annotations.length == 0 ? f.getName() : annotations[0].name();
            try {
                f.setAccessible(true);
                if (f.getClass().isAnnotationPresent(DBName.class)) {
                    Object tmp = gerObject(map, f.getClass());
                    f.set(obj, tmp);
                } else {
                    f.set(obj, map.get(fieldDBName));
                }
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                throw new InitException(CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS);
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
}
