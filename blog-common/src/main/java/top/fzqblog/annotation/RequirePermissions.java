/**
 * 
 */
package top.fzqblog.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import top.fzqblog.po.enums.Logical;
/**
 * @author 抽离
 *
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface RequirePermissions {
	
	 String[] key() default "";
	 Logical logical() default Logical.AND; 
	 
}
