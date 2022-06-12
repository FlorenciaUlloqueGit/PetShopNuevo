package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoFaltantes;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import javafx.print.PageOrientation;
import org.springframework.data.domain.Page;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UsersPDfExporterStockCero {
    private List<Producto> listaProductos;

    public UsersPDfExporterStockCero(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(8);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cod. barras", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoría", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Forma venta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Marca", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Peso", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unidad medida", font));
        table.addCell(cell);



    }
    private void  writeTableData(PdfPTable table){

        for(Producto producto: listaProductos ) {
            table.addCell(String.valueOf(producto.getIdProducto()));
            table.addCell(producto.getNombre());
            table.addCell(String.valueOf(producto.getCodBarras()));
            table.addCell(producto.getCategoria().getNombre());
            table.addCell(producto.getFormaVenta().getNombre());
            table.addCell(producto.getMarca().getNombre());
            table.addCell(String.valueOf(producto.getPesoNeto()));
            table.addCell(producto.getUnidadMedida().getNombre());

            }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
      //  Page pageSize = new Page();

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Listado de productos en falta");

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
