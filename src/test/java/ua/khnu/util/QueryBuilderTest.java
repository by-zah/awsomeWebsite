package ua.khnu.util;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khnu.entity.CatalogRequestParams;
import ua.khnu.entity.SortType;
import ua.khnu.exception.QueryBuildException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryBuilderTest {
    private static final String QUERY_BODY = "SELECT products_attributes.id,\n" +
            "       p.title,\n" +
            "       price,\n" +
            "       photo,\n" +
            "       productID " +
            "FROM products_attributes\n" +
            "         JOIN products p on products_attributes.productID = p.id\n" +
            "         JOIN categories c on p.categoryID = c.id";
    private static final String ORDER_BY_PART = " ORDER BY title DESC ";
    private static final String LIMIT_PART = "LIMIT ?,?";
    private static final String AND = " AND ";
    private static final String PRICE_FROM = "price>=?";
    private static final String PRICE_TO = "price<=?";
    private static final String PRICE_PART = PRICE_FROM + AND + PRICE_TO;
    private static final String CATEGORY_PART = "(c.title=? OR c.title=?)";
    private static final String SIZE_PART = "(size=? OR size=?)";
    private static final String COLOR_PART = "(color=? OR color=?)";
    private static final String WHERE = " WHERE ";
    private static QueryBuilder queryBuilder;
    private CatalogRequestParams crp;

    @BeforeAll
    public static void beforeClass() {
        queryBuilder = new QueryBuilder();
    }

    @BeforeEach
    public void setUp() {
        crp = new CatalogRequestParams();
        crp.setItemFrom(1);
        crp.setItemTo(10);
        crp.setSortType(SortType.ALPHABET_DOWN);
    }

    private void fillAllFields() {
        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        crp.setColor(colors);
        crp.setPriceFrom(0.);
        crp.setPriceTo(100.);
        List<String> size = new ArrayList<>();
        size.add("M");
        size.add("S");
        crp.setSize(size);
        List<String> categories = new ArrayList<>();
        categories.add("одежда");
        categories.add("фигурка");
        crp.setCategory(categories);
    }

    @Test
    public void shouldReturnRequiredQuery() {
        String expected = QUERY_BODY +
                WHERE + PRICE_PART +
                AND + COLOR_PART +
                AND + CATEGORY_PART +
                AND + SIZE_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        fillAllFields();

        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryWithJustBodyOrderAndLimit() {
        String expected = QUERY_BODY +
                ORDER_BY_PART +
                LIMIT_PART;


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryWithoutSizePart() {
        String expected = QUERY_BODY +
                WHERE + PRICE_PART +
                AND + COLOR_PART +
                AND + CATEGORY_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        fillAllFields();
        crp.setSize(null);


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryWithoutPricePart() {
        String expected = QUERY_BODY +
                WHERE +
                COLOR_PART +
                AND + CATEGORY_PART +
                AND + SIZE_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        fillAllFields();
        crp.setPriceFrom(null);
        crp.setPriceTo(null);

        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryWithoutColorPart() {
        String expected = QUERY_BODY +
                WHERE + PRICE_PART +
                AND + CATEGORY_PART +
                AND + SIZE_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        fillAllFields();
        crp.setColor(null);


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryWithoutCategoryPart() {
        String expected = QUERY_BODY +
                WHERE + PRICE_PART +
                AND + COLOR_PART +
                AND + SIZE_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        fillAllFields();
        crp.setCategory(null);


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryJustWithPricePart() {
        String expected = QUERY_BODY +
                WHERE + PRICE_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        crp.setPriceFrom(0.);
        crp.setPriceTo(100.);


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryJustWithPriceFrom() {
        String expected = QUERY_BODY +
                WHERE + PRICE_FROM +
                ORDER_BY_PART +
                LIMIT_PART;
        crp.setPriceFrom(0.);


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnQueryJustWithCategories() {
        String expected = QUERY_BODY +
                WHERE + CATEGORY_PART +
                ORDER_BY_PART +
                LIMIT_PART;
        List<String> categories = new ArrayList<>();
        categories.add("одежда");
        categories.add("фигурка");
        crp.setCategory(categories);


        String actual = queryBuilder.getQueryFromCategoryRequestParams(crp);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowQueryBuilderExceptionOnEmptyCRP() {
        assertThrows(QueryBuildException.class
                , () -> queryBuilder.getQueryFromCategoryRequestParams(new CatalogRequestParams()));
    }

    @Test
    void shouldReturnArrayWithRequiredOrder() {
        fillAllFields();
        Object[] expected = {crp.getPriceFrom(), crp.getPriceTo(),
                crp.getColor().get(0), crp.getColor().get(1),
                crp.getCategory().get(0), crp.getCategory().get(1),
                crp.getSize().get(0), crp.getSize().get(1),
                crp.getItemFrom() - 1, crp.getItemTo() - crp.getItemFrom()};


        Object[] actual = queryBuilder.getParams(crp);


        assertArrayEquals(expected, actual);
    }
}
