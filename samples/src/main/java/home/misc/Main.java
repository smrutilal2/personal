/*
 * Copyright (C) 2003-2010, RSA Security Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of RSA Security Inc, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. RSA Security Inc AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL RSA Security Inc
 * OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 * LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 * EVEN IF RSA Security Inc HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of
 * any nuclear facility.
 */
package home.misc;

import home.algo.Util;

/**
 * @author sahoos2
 * 
 */
public class Main {

	public int[] mergeSort(int[] list) {

		if (list.length == 1)
			return list;

		int[] left = new int[list.length / 2];
		int[] right = new int[list.length - left.length];

		System.arraycopy(list, 0, left, 0, left.length);
		System.arraycopy(list, left.length, right, 0, right.length);

		left = mergeSort(left);
		right = mergeSort(right);

		int[] result = merge(left, right);
		return result;
	}

	private int[] merge(int[] left, int[] right) {

		int[] result = new int[left.length + right.length];
		int index = 0;
		while (left.length > 0 || right.length > 0) {

			if (left.length > 0 && right.length > 0) {
				if (left[0] <= right[0]) {
					result[index++] = left[0];
					left = Util.rest(left);
				} else {
					result[index++] = right[0];
					right = Util.rest(right);
				}
			} else if (left.length > 0) {
				result[index++] = left[0];
				left = Util.rest(left);
			} else {
				result[index++] = right[0];
				right = Util.rest(right);
			}
		}

		return result;
	}

	public static void main(String[] args) {

		Main main = new Main();

		int[] arrayToSort = { 12, 9, 4, 99, 120, 1, 3, 10 };
		Util.print(main.mergeSort(arrayToSort));
	}
}
