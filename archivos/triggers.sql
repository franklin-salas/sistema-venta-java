DELIMITER //
CREATE TRIGGER tr_updStockIngreso AFTER INSERT ON detalle_ingreso
 FOR EACH ROW BEGIN
 UPDATE articulo SET stock = stock + NEW.cantidad 
 WHERE articulo.id = NEW.articulo_id;
END
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER tr_updStockIngresoAnular AFTER UPDATE ON ingreso FOR EACH ROW 
BEGIN
  UPDATE articulo a
    JOIN detalle_ingreso di
      ON di.articulo_id = a.id
     AND di.ingreso_id = new.id
     set a.stock = a.stock - di.cantidad;
end;
//
DELIMITER ;


/* ventas*/

DELIMITER //
CREATE TRIGGER tr_updStockVenta AFTER INSERT ON detalle_venta
 FOR EACH ROW BEGIN
 UPDATE articulo SET stock = stock - NEW.cantidad 
 WHERE articulo.id = NEW.articulo_id;
END
//
DELIMITER ;

/*anular venta*/
DELIMITER //
CREATE TRIGGER tr_updStockVentaAnular AFTER UPDATE ON venta FOR EACH ROW 
BEGIN
  UPDATE articulo a
    JOIN detalle_venta dv
      ON dv.articulo_id = a.id
     AND dv.venta_id = new.id
     set a.stock = a.stock + dv.cantidad;
end;
//
DELIMITER ;