package com.example.york.teamcraft.schedulefragment.viewmodel;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.view.View;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by York on 2017/11/4.
 */

public class OutputPdf {
    // create a new document
    private PdfDocument document = new PdfDocument();

    public void outputDoc(View content, Fragment frag) {
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(100, 100, 1).setContentRect(new Rect(0, 0, 90, 90)).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        // draw something on the page
        content.draw(page.getCanvas());

        // finish the page
        document.finishPage(page);

        // add more pages

        // write the document content
//        document.writeTo(new OutputStream());

        // close the document
        document.close();

    }


}
