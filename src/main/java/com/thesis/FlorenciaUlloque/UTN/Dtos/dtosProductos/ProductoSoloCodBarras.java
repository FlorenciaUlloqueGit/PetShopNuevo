package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

public class ProductoSoloCodBarras {
    private long codBarras;

    public ProductoSoloCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }

    public ProductoSoloCodBarras() {
    }

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }
}
