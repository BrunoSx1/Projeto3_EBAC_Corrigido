/**
 * 
 */
package anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rodrigo.pires
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tabela {

	String value();
	// Novos campos adicionados para mapear a tabela no banco de dados
    // Adicione novos campos conforme necessário
    String primaryKey() default ""; // Nome da coluna que serve como chave primária
    String foreignKey() default ""; // Nome da coluna que serve como chave estrangeira
}