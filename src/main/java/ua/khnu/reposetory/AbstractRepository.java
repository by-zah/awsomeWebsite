package ua.khnu.reposetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public abstract class AbstractRepository<T> {
    protected JdbcTemplate jdbcAccessor;
    protected String[] columnNames;
    private Class<T> genClass;
    @Autowired
    public AbstractRepository(JdbcTemplate jdbcAccessor){
        this.jdbcAccessor = jdbcAccessor;
        initColumnNames();
        genClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void initColumnNames(){

        String[] str = Arrays.stream(genClass.getDeclaredFields())
                .map(Field::getName).toArray(String[]::new);
        columnNames = new String[str.length-1];
        System.arraycopy(str,1,columnNames,0,str.length-1);
    }
    private List<T> getObjectListFromResultList(List<Map<String, Object>> resList) {
        return null;
//        return resList.stream().map(m -> {
//            T obj = Arrays.stream(genClass.getDeclaredConstructors()).filter(c->c.getParameterCount()==0).findAny().;
//            user.setId((Integer) m.get(ID));
//            user.setPassword((String) m.get(PASSWORD));
//            user.setEmail((String) m.get(EMAIL));
//            user.setNumber((String) m.get(CONTACT_NUMBER));
//            user.setMailEnable((Boolean) m.get(MAILING_ENABLED));
//            return user;
//        }).collect(Collectors.toList());
    }
}
