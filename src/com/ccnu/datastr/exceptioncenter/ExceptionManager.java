package com.ccnu.datastr.exceptioncenter;

import com.ccnu.datastr.exceptioncenter.exception.UnsupportedFileTypeException;

public class ExceptionManager {

	public static void throwUnsupportedFileTypeException() {
		try {
			throw new UnsupportedFileTypeException();
		} catch (UnsupportedFileTypeException e) {
			e.printStackTrace();
		}
	}
}
