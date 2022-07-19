package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoReporte2;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReporteVentasHoyPDF {
    private List<ProductoReporte2> reporte2List;
    private LocalDate fechaDiaria;

    public ReporteVentasHoyPDF(List<ProductoReporte2> reporte2List, LocalDate fechaDiaria) {
        this.reporte2List = reporte2List;
        this.fechaDiaria = fechaDiaria;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(8);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoría", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Forma de venta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Precio de venta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad vendida", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("N° cuotas", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Porc. Interés", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Subtotal en AR$", font));
        table.addCell(cell);




    }
    private void  writeTableData(PdfPTable table){

        for(ProductoReporte2 producto: reporte2List ) {
            table.addCell(producto.getNombre());
            table.addCell(producto.getCategoria());
            table.addCell(producto.getFormaVenta());
            table.addCell(String.valueOf(producto.getPrecioVenta()));
            table.addCell(String.valueOf(producto.getCantidad()));
            table.addCell(String.valueOf(producto.getCantidadCuotas()));
            table.addCell(String.valueOf(producto.getPorcentajeInteres()));
            table.addCell(String.valueOf(producto.getTotal()));

            }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
      //  Page pageSize = new Page();

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Listado de productos vendidos en el día " + fechaDiaria);

        document.add(new Paragraph(titulo));

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
