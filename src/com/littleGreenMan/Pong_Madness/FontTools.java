package com.littleGreenMan.Pong_Madness;

import android.content.Context;
import android.graphics.Typeface;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: elodieferrais
 * Date: 3/23/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class FontTools {

    protected static final String FONT_FOLDER = "Fonts";

    public static Typeface getTypefaceFromRessource(final int resourceId, final Context context) {
        Typeface tf = null;
        final File fontBaseDir = context.getDir(FONT_FOLDER, Context.MODE_PRIVATE);
        final String fontName = context.getResources().getResourceEntryName(resourceId);

        final InputStream is = context.getResources().openRawResource(resourceId);
        final File f = new File(fontBaseDir, fontName);

        // If the font file does not exist already => create it
        if (!f.exists()) {

            // Read resource and write temp file
            try {
                final byte[] buffer = new byte[is.available()];
                final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f.getAbsolutePath()));

                int l = 0;
                while ((l = is.read(buffer)) > 0)
                {
                    bos.write(buffer, 0, l);
                }
                bos.close();

            } catch (final IOException e) {
                return null;
            }
        }

        tf = Typeface.createFromFile(f.getAbsolutePath());
        return tf;
    }
}
