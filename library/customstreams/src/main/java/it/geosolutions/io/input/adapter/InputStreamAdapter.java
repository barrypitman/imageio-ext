/*
 *    ImageI/O-Ext - OpenSource Java Image translation Library
 *    http://www.geo-solutions.it/
 *    https://imageio-ext.dev.java.net/
 *    (C) 2007 - 2008, GeoSolutions
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package it.geosolutions.io.input.adapter;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.ImageInputStream;

/**
 */
public final class InputStreamAdapter extends InputStream {

	ImageInputStream stream;

	public InputStreamAdapter(ImageInputStream stream) {

		this.stream = stream;
	}

	public void close() throws IOException {
		stream.close();
	}

	public void mark(int readlimit) {
		stream.mark();
	}

	public boolean markSupported() {
		return true;
	}

	public int read() throws IOException {
		return stream.read();
	}

	public int read(byte b[], int off, int len) throws IOException {
		return stream.read(b, off, len);
	}

	public void reset() throws IOException {
		stream.reset();
	}

	public long skip(long n) throws IOException {
		return stream.skipBytes(n);
	}

	public ImageInputStream getWrappedStream() {
		return stream;
	}
}