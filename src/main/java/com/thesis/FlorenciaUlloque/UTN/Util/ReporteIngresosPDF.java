package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReporteIngresosPDF {
    private List<IngresoProductos> listaProductos;

    public ReporteIngresosPDF(List<IngresoProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("Fecha", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Proveedor", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(" Forma de pago efectuada", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);
    }
    private void  writeTableData(PdfPTable table){

        for(IngresoProductos ingreso: listaProductos ) {
            table.addCell(String.valueOf(ingreso.getFecha()));
            table.addCell(ingreso.getProveedor().getNombre());
            table.addCell(ingreso.getFormaPago().getNombre());
            table.addCell(String.valueOf(ingreso.getTotal()));
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        //  Page pageSize = new Page();

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Listado mensual de movimiento económico por ingreso de mercadería");

        document.add(new Paragraph(titulo));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
