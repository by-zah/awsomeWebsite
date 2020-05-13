package ua.khnu.reposetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.exception.InitException;
import ua.khnu.util.DBName;

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
        try {
            return Optional.ofNullable(jdbcAccessor.queryForObject(query, Integer.class, args));
        } catch (EmptyResultDataAccessException e){
            return Optional.of(0);
        }
    }

    protected List<T> getObjectListFromResultList(List<Map<String, Object>> resList) {
        return resList.stream().map(m -> {
            try {
                T obj = (T) Arrays.stream(genClass.getDeclaredConstructors()).filter(c -> c.getParameterCount() == 0).findAny().get().newInstance();
                Arrays.stream(genClass.getDeclaredFields()).forEach(f -> {
                    DBName[] annotations = f.getAnnotationsByType(DBName.class);
                    String fieldDBName = annotations.length == 0 ? f.getName() : annotations[0].name();
                    try {
                        f.setAccessible(true);
                        f.set(obj, m.get(fieldDBName));
                    } catch (IllegalAccessException e) {
                        throw new InitException(CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS);
                    }
                });
                return obj;
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                throw new InitException(CAN_NOT_READ_COLUMN_PROPERTIES_FOR_THIS_CLASS);
            }
        }).collect(Collectors.toList());
    }
}
