package com.taquistore.repository;

import com.taquistore.entity.ProductoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
    
    /*
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON LA CATEGORIA DEL ID #2
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c "
        + "WHERE p.categoriaEntity.id_categoria = 2")
    List<ProductoEntity> findAllByCategoriaID2();
    
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON UNA CATEGORIA ESPECÍFICA
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c "
            + "WHERE c.id_categoria = :categoriaId")
    List<ProductoEntity> findAllByCategoriaIdParam(@Param("categoriaId") Long categoriaId);

    // SELECCIONAR PRODUCTOS QUE ESTEN DE OFERTA (EL VALOR DEL ATRIBUTO OFERTA ES TRUE)
    @Query(value = "SELECT p FROM ProductoEntity p WHERE p.oferta = true")
    List<ProductoEntity> findAllByOfertaTrue();
    
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON LA MARCA DEL ID #1, #2 Y #3
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.marcaEntity m "
        + "WHERE p.marcaEntity.id_marca IN (1, 2, 3)")
    List<ProductoEntity> findAllByMarcaID123();
    
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON VARIAS MARCAS ESPECIFICAS
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.marcaEntity m "
        + "WHERE m.id_marca IN :marcaIds")
    List<ProductoEntity> findAllByMarcaIDsParams(@Param("marcaIds") List<Long> marcaIds);

    
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON UNA CATEGORIA ESPECÍFICA
    // +
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON VARIAS MARCAS ESPECIFICAS
    // = 
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON UNA CATEGORIA ESPECIFICA Y VARIAS MARCAS ESPECIFICAS
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE c.id_categoria = :categoriaId AND m.id_marca IN :marcaIds")
    List<ProductoEntity> findAllByCategoriaIdParamAndMarcaIDsParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds);
    
    
    
    // SELECCIONAR PRODUCTOS QUE ESTEN DE OFERTA (EL VALOR DEL ATRIBUTO OFERTA ES TRUE)
    // +
    // SELECCIONAR PRODUCTOS QUE COINCIDAN CON VARIAS MARCAS ESPECIFICAS
    // =
    // SELECCIONAR PRODUCTOS QUE ESTEN DE OFERTA Y COINCIDAN CON VARIAS MARCAS ESPECIFICAS
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.marcaEntity m "
            + "WHERE p.oferta = true AND m.id_marca IN :marcaIds")
    List<ProductoEntity> findAllByOfertaTrueAndMarcaIDsParams(
            @Param("marcaIds") List<Long> marcaIds);    
    
    
    
    
    
    
    */
    // SELECCIONAR PRODUCTOS POR VARIOS PARAMETROS DE BUSQUEDA
    /*
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            
            + "AND ((p.oferta = true AND p.preciooferta >= :minPrecio AND p.preciooferta <= :maxPrecio) "
            + "OR (p.oferta = false AND p.precionormal >= :minPrecio AND p.precionormal <= :maxPrecio)) "
            
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :palabraClave, '%'))) "
            + "AND p.estado = true"
    )
    List<ProductoEntity> findAllByFiltros(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );
*/
    
    
    // SELECCIONAR PRODUCTOS HABILITADOS POR VARIOS PARAMETROS DE BUSQUEDA:
    // 1 CATEGORIA
    // 1 o MÁS MARCAS
    // PRECIO MINIMO Y MAXIMO
    // ¿EN OFERTA?
    //...
    
    /*
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "      (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "      (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio))) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :palabraClave, '%'))) "
            + "AND p.estado = true "
            + "AND c.estado = true "
            + "AND m.estado = true "
            + "ORDER BY p.id_producto DESC"
    )
    List<ProductoEntity> findAllByEstadoTrueAndParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );
    
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "     (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "     (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio)) OR "
            + "     (:minPrecio IS NOT NULL AND :maxPrecio IS NOT NULL AND p.preciooferta IS NULL)) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR CONCAT(' ', p.id_producto, p.nombre, p.precionormal, m.nombre, c.nombre) LIKE %:palabraClave%) "
            + "AND p.estado = true "
            + "AND c.estado = true "
            + "AND m.estado = true "
            + "ORDER BY p.id_producto DESC"
    )
    List<ProductoEntity> findAllByEstadoTrueAndParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );

*/
    /* PARA EL USUARIO (FILTRAR LOS PRODUCTOS POR PARAMETROS)*/
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "     (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "     (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio))) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR CONCAT(' ', p.nombre, m.nombre, c.nombre) LIKE %:palabraClave%) "
            + "AND p.estado = true "
            + "AND c.estado = true "
            + "AND m.estado = true "
            + "ORDER BY p.id_producto DESC"
    )
    List<ProductoEntity> findAllByEstadoTrueAndParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );

    /* CANTIDAD NUMERICA */
    @Query("SELECT COUNT(p) FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "     (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "     (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio))) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR CONCAT(' ', p.nombre, m.nombre, c.nombre) LIKE %:palabraClave%) "
            + "AND p.estado = true "
            + "AND c.estado = true "
            + "AND m.estado = true"
    )
    Long countAllByEstadoTrueAndParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );


    /*
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "      (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "      (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio))) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :palabraClave, '%'))) "
            + "ORDER BY p.id_producto DESC"
    )
    List<ProductoEntity> findAllByParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );
    /* PARA EL ADMINISTRADOR */
    @Query(value = "SELECT p FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "     (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "     (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio))) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR CONCAT(' ', p.id_producto, p.nombre, p.precionormal, p.preciooferta, "
            + "     m.nombre, c.nombre) LIKE %:palabraClave%) "
            + "ORDER BY p.id_producto DESC"
    )
    List<ProductoEntity> findAllByParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );

    @Query(value = "SELECT COUNT(p) FROM ProductoEntity p JOIN p.categoriaEntity c JOIN p.marcaEntity m "
            + "WHERE (:categoriaId IS NULL OR c.id_categoria = :categoriaId) "
            + "AND (COALESCE(:marcaIds, NULL) IS NULL OR m.id_marca IN :marcaIds) "
            + "AND ((:minPrecio IS NULL AND :maxPrecio IS NULL) OR "
            + "     (p.oferta = true AND (:minPrecio IS NULL OR p.preciooferta >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.preciooferta <= :maxPrecio)) OR "
            + "     (p.oferta = false AND (:minPrecio IS NULL OR p.precionormal >= :minPrecio) "
            + "             AND (:maxPrecio IS NULL OR p.precionormal <= :maxPrecio))) "
            + "AND (:enOferta IS NULL OR p.oferta = :enOferta) "
            + "AND (:palabraClave IS NULL OR CONCAT(' ', p.id_producto, p.nombre, p.precionormal, p.preciooferta, "
            + "     m.nombre, c.nombre) LIKE %:palabraClave%) "
            + "ORDER BY p.id_producto DESC"
    )
    Long countAllByParams(
            @Param("categoriaId") Long categoriaId, 
            @Param("marcaIds") List<Long> marcaIds,
            @Param("minPrecio") Double minPrecio,
            @Param("maxPrecio") Double maxPrecio,
            @Param("enOferta") Boolean enOferta,
            @Param("palabraClave") String palabraClave
    );

    
}