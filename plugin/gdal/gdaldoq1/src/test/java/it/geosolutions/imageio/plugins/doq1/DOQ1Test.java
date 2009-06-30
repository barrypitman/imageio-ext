/*
 *    ImageI/O-Ext - OpenSource Java Image translation Library
 *    http://www.geo-solutions.it/
 *    https://imageio-ext.dev.java.net/
 *    (C) 2007 - 2009, GeoSolutions
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    either version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package it.geosolutions.imageio.plugins.doq1;

import it.geosolutions.imageio.gdalframework.AbstractGDALTest;
import it.geosolutions.imageio.gdalframework.Viewer;
import it.geosolutions.resources.TestData;

import java.awt.RenderingHints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageReadParam;
import javax.media.jai.ImageLayout;
import javax.media.jai.JAI;
import javax.media.jai.ParameterBlockJAI;
import javax.media.jai.RenderedOp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testing reading capabilities for {@link DOQ1ImageReader}.
 * 
 * @author Daniele Romagnoli, GeoSolutions.
 * @author Simone Giannecchini, GeoSolutions.
 */
public class DOQ1Test extends AbstractGDALTest {
    public final static String fileName = "fakedoq1.doq";

    /**
     * Test read exploiting common JAI operations (Crop-Translate-Rotate)
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void imageRead() throws FileNotFoundException, IOException {
        if (!isGDALAvailable) {
            return;
        }
        File file;
        try {
            file = TestData.file(this, fileName);
        } catch (FileNotFoundException fnfe) {
            LOGGER.info("Test file not available");
            return;
        }
        // ////////////////////////////////////////////////////////////////
        // preparing to read
        // ////////////////////////////////////////////////////////////////
        final ParameterBlockJAI pbjImageRead;
        final ImageReadParam irp = new ImageReadParam();

        pbjImageRead = new ParameterBlockJAI("ImageRead");
        pbjImageRead.setParameter("Input", file);
        pbjImageRead.setParameter("readParam", irp);

        final ImageLayout l = new ImageLayout();
        l.setTileGridXOffset(0).setTileGridYOffset(0).setTileHeight(32).setTileWidth(32);

        // get a RenderedImage
        RenderedOp image = JAI.create("ImageRead", pbjImageRead,new RenderingHints(JAI.KEY_IMAGE_LAYOUT, l));

        if (TestData.isInteractiveTest()) {
            Viewer.visualizeAllInformation(image, "test");
        } else
        Assert.assertNotNull(image.getTiles());
        Assert.assertEquals(500, image.getWidth());
        Assert.assertEquals(500, image.getHeight());
    }

}
