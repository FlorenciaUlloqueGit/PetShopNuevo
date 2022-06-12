package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoReporteBolsaCerrada;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReporteVentasBolsasPDF {
    private List<ProductoReporteBolsaCerrada> listaProductos;

    public ReporteVentasBolsasPDF(List<ProductoReporteBolsaCerrada> listaProductos) {
        this.listaProductos = listaProductos;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(7);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoría", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Marca", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Peso", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unidad de medida ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);




    }
    private void  writeTableData(PdfPTable table){

        for(ProductoReporteBolsaCerrada producto: listaProductos ) {
            table.addCell(String.valueOf(producto.getIdProducto()));
            table.addCell(producto.getNombre());
            table.addCell(producto.getCategoria().getNombre());
            table.addCell(producto.getMarca().getNombre());
            table.addCell(String.valueOf(producto.getPesoNeto()));
            table.addCell(producto.getUnidadMedida().getNombre());
            table.addCell(String.valueOf(producto.getCantidad()));

            }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
      //  Page pageSize = new Page();

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Listado mensual de bolsas cerradas de balanceado vendidas ");

        document.add(new Paragraph(titulo));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
