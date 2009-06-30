/*
 *    JImageIO-extension - OpenSource Java Image translation Library
 *    http://www.geo-solutions.it/
 *    (C) 2007, GeoSolutions
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
package it.geosolutions.imageio.plugins.grib1;

import it.geosolutions.imageio.ndplugin.BaseImageMetadata;
import it.geosolutions.imageio.utilities.ImageIOUtilities;
import it.geosolutions.resources.TestData;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;

import org.junit.Assert;

public class GRIB1ReadTest  {

    private static final Logger LOGGER = Logger
            .getLogger("it.geosolutions.imageio.plugins.grib1");

    private void warningMessage() {
        StringBuffer sb = new StringBuffer(
                "Test file not available. Test are skipped");
        LOGGER.info(sb.toString());
    }

    final static String fileName = "NETTUNO_00.grb";

    final static String dataPathPrefix = "D:\\work\\Data\\rixen\\lscv08\\METEOAM\\NETTUNO_CNMCA_2008101400\\";


    /**
     * Simple read from a Grib File containing a lot of records
     * 
     * @throws IOException
     */
    @org.junit.Test
    public void testReadSingleFile() throws IOException {
        final File inputFile = new File(dataPathPrefix+fileName);
        if (!inputFile.exists()) {
            warningMessage();
            return;
        }
        final ImageReader reader = new GRIB1ImageReaderSpi()
                .createReaderInstance();
        reader.setInput(inputFile);
        final int index = 0;
        final ImageReadParam param = new ImageReadParam();
        param.setSourceSubsampling(2, 2, 0, 0);

        RenderedImage ri = reader.read(index, param);
        if (TestData.isInteractiveTest())
            ImageIOUtilities.visualize(ri);
        else
            Assert.assertNotNull(ri.getData());
        ImageIOUtilities.displayImageIOMetadata(reader.getImageMetadata(index)
                .getAsTree(BaseImageMetadata.nativeMetadataFormatName));
        ImageIOUtilities.displayImageIOMetadata(reader.getImageMetadata(index)
                .getAsTree(GRIB1ImageMetadata.nativeMetadataFormatName));
        reader.dispose();
    }
}
