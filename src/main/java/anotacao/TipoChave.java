package anotacao;

import java.lang.annotation.*;

/**
 * Annotation used to specify the type of key for a field.
 * 
 * @author rodrigo.pires
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoChave {

    String value(); // Nome do método getter que representa a chave
    
    // Novos campos adicionados para fornecer mais informações sobre a chave
    // Adicione novos campos conforme necessário
    boolean isPrimaryKey() default false; // Indica se a chave é primária
    boolean isForeignKey() default false; // Indica se a chave é estrangeira
    String foreignTable() default ""; // Tabela referenciada pela chave estrangeira
    String foreignColumn() default ""; // Coluna referenciada pela chave estrangeira
}