package ua.khnu.db.transaction;
import java.lang.annotation.*;

/**
 * if method annotated by this annotation
 * and instance of class created by TransactionHandler proxy
 * it will be transaction
 */
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Transaction {
}
