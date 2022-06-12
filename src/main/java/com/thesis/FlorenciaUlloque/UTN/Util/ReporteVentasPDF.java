package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReporteVentasPDF {
    private List<SalidaProducto> salidaProductos;
    private float totalVentas;

    public ReporteVentasPDF(List<SalidaProducto> salidaProductos, float totalVentas ) {
            this.salidaProductos = salidaProductos;
            this.totalVentas = totalVentas;
        }
    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("ID venta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("fecha", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Forma de pago", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cliente", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Subtotal en AR$", font));
        table.addCell(cell);
    }
    private void  writeTableData(PdfPTable table){

        for(SalidaProducto salidaProducto: salidaProductos ) {
            table.addCell(String.valueOf(salidaProducto.getIdEgreso()));
            table.addCell(String.valueOf(salidaProducto.getFecha()));
            table.addCell(salidaProducto.getFormaPago().getNombre());
            table.addCell(salidaProducto.getCliente().getEmail());
            table.addCell(String.valueOf(salidaProducto.getTotal()));
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Ingresos económicos mensuales generados por ventas");

        document.add(new Paragraph(titulo));


        Paragraph subtitulo = new Paragraph("Total final en AR$ " + totalVentas);
        subtitulo.setSpacingBefore(10);

        document.add(new Paragraph(subtitulo));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
