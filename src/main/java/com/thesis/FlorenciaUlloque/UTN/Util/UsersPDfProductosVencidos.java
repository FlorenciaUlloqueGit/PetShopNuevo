package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UsersPDfProductosVencidos {
    private List<Producto> listaProductos;

    public UsersPDfProductosVencidos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(9);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoría", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Forma venta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Animal", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Fecha vencimiento", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Peso", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unidad medida", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad en medida", font));
        table.addCell(cell);



    }
    private void  writeTableData(PdfPTable table){

        for(Producto producto: listaProductos ) {
            table.addCell(String.valueOf(producto.getIdProducto()));
            table.addCell(producto.getNombre());
            table.addCell(producto.getCategoria().getNombre());
            table.addCell(producto.getFormaVenta().getNombre());
            table.addCell(producto.getTipoAnimal().getNombre());
            table.addCell(String.valueOf(producto.getFechaVencimiento()));
            table.addCell(String.valueOf(producto.getPesoNeto()));
            table.addCell(producto.getUnidadMedida().getNombre());
            table.addCell(String.valueOf(producto.getStock().getCantidad()));

            }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
      //  Page pageSize = new Page();

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Listado de productos vencidos");

        document.add(new Paragraph(titulo));

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        if(table.getRows().stream().count() < 1){

            Paragraph explicacion = new Paragraph("No hay productos vencidos stock en este momento");
            explicacion.setSpacingBefore(15);
            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(Color.black);
            font.setSize(10);
            explicacion.setFont(font);
        }


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
