package com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks;

public class StockDtosProdID {
        private int idProducto;
        private int cantidad;

        public int getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(int idProducto) {
            this.idProducto = idProducto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public StockDtosProdID(int idProducto, int cantidad) {
            this.idProducto = idProducto;
            this.cantidad = cantidad;
        }

        public StockDtosProdID() {
        }

}
