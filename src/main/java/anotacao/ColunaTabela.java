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
 * Annotation used to mark a field as a database column.
 * 
 * @author rodrigo.pires
 *
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColunaTabela {

	String dbName();
	
	String setJavaName();
	
	// Novos campos adicionados para mapear a tabela no banco de dados
	// Adicione novos campos conforme necessário
	String getJavaName() default ""; // Nome do método getter (ex: getEmail)
	String columnType() default ""; // Tipo da coluna no banco de dados (ex: VARCHAR, INTEGER, etc.)
	int length() default 255; // Comprimento da coluna no banco de dados
	boolean isPrimaryKey() default false; // Indica se a coluna é chave primária
	boolean isForeignKey() default false; // Indica se a coluna é chave estrangeira
}
