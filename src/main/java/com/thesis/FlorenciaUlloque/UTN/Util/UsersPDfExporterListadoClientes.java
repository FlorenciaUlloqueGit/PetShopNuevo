package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UsersPDfExporterListadoClientes {
    private List<ClienteDto> listUsers;

    public UsersPDfExporterListadoClientes(List<ClienteDto> listUsers) {
        this.listUsers = listUsers;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Apellido", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Teléfono", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Dirección", font));
        table.addCell(cell);



    }
    private void  writeTableData(PdfPTable table){

        for(ClienteDto cliente: listUsers ) {
            if(cliente.getIdCliente()!= 2) {
                table.addCell(String.valueOf(cliente.getIdCliente()));
                table.addCell(cliente.getNombre());
                table.addCell(cliente.getApellido());
                table.addCell(cliente.getEmail());
                table.addCell(String.valueOf(cliente.getTelefono()));
                table.addCell(cliente.getDireccion());
            }
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Listado de clientes:");

        document.add(new Paragraph(titulo));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
