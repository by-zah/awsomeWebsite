package ua.khnu.util;

import org.springframework.stereotype.Component;
import ua.khnu.entity.CatalogRequestParams;
import ua.khnu.entity.SortType;
import ua.khnu.exception.QueryBuildException;

import java.util.*;

import static ua.khnu.entity.SortType.*;

@Component
public class QueryBuilder {
    private static final String CAN_NOT_BUILD_QUERY = "can not build query";
    private static final String WHERE = "WHERE";
    private static final String SPACE = " ";
    private static final String GET_PRODUCTS_BY_PARAMS_BODY = "SELECT products_attributes.id as id,\n" +
            "       p.title as title,\n" +
            "       price,\n" +
            "       photo\n" +
            "FROM products_attributes\n" +
            "         JOIN products p on products_attributes.productID = p.id\n" +
            "         JOIN categories c on p.categoryID = c.id";
    private Map<SortType, String> sortTypes;

    public QueryBuilder() {
        initSortTypes();
    }

    public Object[] getParams(CatalogRequestParams crp) {
        List<Object> params = new ArrayList<>();
        Optional.ofNullable(crp.getPriceFrom()).ifPresent(params::add);
        Optional.ofNullable(crp.getPriceTo()).ifPresent(params::add);
        Optional.ofNullable(crp.getColor()).ifPresent(params::addAll);
        Optional.ofNullable(crp.getCategory()).ifPresent(params::addAll);
        Optional.ofNullable(crp.getSize()).ifPresent(params::addAll);
        params.add(crp.getItemFrom() - 1);
        params.add(crp.getItemTo() - crp.getItemFrom());
        return params.toArray();
    }

    public String getQueryFromCategoryRequestParams(CatalogRequestParams crp) {
        validateCrp(crp);
        StringBuilder query = new StringBuilder(GET_PRODUCTS_BY_PARAMS_BODY);
        boolean isWhereAdded = false;
        if (crp.getPriceFrom() != null && crp.getPriceTo() != null) {
            isWhereAdded = addWhere(query, false);
            query.append("price>=? AND price<=?").append(SPACE);
        } else if (crp.getPriceFrom() != null && crp.getPriceTo() == null) {
            isWhereAdded = addWhere(query, false);
            query.append("price>=?").append(SPACE);
        } else if (crp.getPriceFrom() == null && crp.getPriceTo() != null) {
            isWhereAdded = addWhere(query, false);
            query.append("price<=?").append(SPACE);
        }
        isWhereAdded = addParamsFromList(crp.getColor(), query, "color", isWhereAdded);
        isWhereAdded = addParamsFromList(crp.getCategory(), query, "c.title", isWhereAdded);
        isWhereAdded = addParamsFromList(crp.getSize(), query, "size", isWhereAdded);
        if (!isWhereAdded) {
            query.append(SPACE);
        }
        query.append("GROUP BY (p.id)").append(SPACE);
        query.append(sortTypes.get(crp.getSortType())).append(SPACE);
        query.append("LIMIT ?,?");
        return query.toString();
    }

    private boolean addParamsFromList(List<String> list, StringBuilder query, String alias, boolean isWhereAdded) {
        if (list != null) {
            if (isWhereAdded) {
                query.append("AND").append(SPACE);
            }
            isWhereAdded = addWhere(query, isWhereAdded);
            query.append("(");
            if (!list.isEmpty()) {
                query.append(alias).append("=?").append(SPACE);
            }
            String part = "OR " + alias + "=?" + SPACE;
            query.append(part.repeat(Math.max(0, list.size() - 1)));
            query.replace(query.length() - 1, query.length() - 1, ")");
        }
        return isWhereAdded;
    }

    private boolean addWhere(StringBuilder query, boolean isWhereAdded) {
        if (!isWhereAdded) {
            query.append(SPACE).append(WHERE).append(SPACE);
        }
        return true;
    }

    private void initSortTypes() {
        sortTypes = new EnumMap<>(SortType.class);
        sortTypes.put(PRICE_UP, "ORDER BY price");
        sortTypes.put(PRICE_DOWN, "ORDER BY price DESC");
        sortTypes.put(ALPHABET_UP, "ORDER BY title");
        sortTypes.put(ALPHABET_DOWN, "ORDER BY title DESC");
    }

    private void validateCrp(CatalogRequestParams crp) {
        if (crp.getSortType() == null) {
            throw new QueryBuildException(CAN_NOT_BUILD_QUERY + " without sortType");
        }
        if (crp.getItemFrom() == null) {
            throw new QueryBuildException(CAN_NOT_BUILD_QUERY + " without start item index");
        }
        if (crp.getItemTo() == null) {
            throw new QueryBuildException(CAN_NOT_BUILD_QUERY + " without finish item index");
        }
        if (crp.getItemFrom() < 0) {
            throw new QueryBuildException("can not build query with negative itemFrom value");
        }
        if (crp.getItemTo() < 0) {
            throw new QueryBuildException("can not build query with negative itemTo value");
        }
        if (crp.getItemTo() - crp.getItemFrom() < 0) {
            throw new QueryBuildException("start item index can not be less then finish item index");
        }
    }
}
