/*
 * Copyright (C) 2014 Jason Taylor.
 * Released as open-source under the Apache License, Version 2.0.
 * 
 * =\/==========================================================================
 * 
 * Copyright (C) 2014 Jason Taylor
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * =============================================================================
 * 
 * This class contains code from http://riven8192.blogspot.com, copyright Riven
 * and licensed under CC BY 3.0.
 * 
 * =\/==========================================================================
 * 
 * Copyright (c) 2009, 2012 + any other year Riven
 * All rights reserved.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * 
 */
package Maths.generation;

/**
 * Thanks to Riven:
 * <p>
 * http://riven8192.blogspot.com/2009/08/fastmath-sincos-lookup-tables.html
 * http://riven8192.blogspot.com/2009/08/fastmath-atan2-lookup-table.html
 * 
 * @author Riven
 */
public class TrigLUT {

  public static final float sin(float rad) {
    return sin[(int) (rad * radToIndex) & SIN_MASK];
  }

  public static final float cos(float rad) {
    return cos[(int) (rad * radToIndex) & SIN_MASK];
  }

  public static final float sinDeg(float deg) {
    return sin[(int) (deg * degToIndex) & SIN_MASK];
  }

  public static final float cosDeg(float deg) {
    return cos[(int) (deg * degToIndex) & SIN_MASK];
  }

  // private static final float RAD, DEG;
  private static final int SIN_BITS, SIN_MASK, SIN_COUNT;
  private static final float radFull, radToIndex;
  private static final float degFull, degToIndex;
  private static final float[] sin, cos;

  static {
    // RAD = (float) Math.PI / 180.0f;
    // DEG = 180.0f / (float) Math.PI;

    SIN_BITS = 12;
    SIN_MASK = ~(-1 << SIN_BITS);
    SIN_COUNT = SIN_MASK + 1;

    radFull = (float) (Math.PI * 2.0);
    degFull = (float) (360.0);
    radToIndex = SIN_COUNT / radFull;
    degToIndex = SIN_COUNT / degFull;

    sin = new float[SIN_COUNT];
    cos = new float[SIN_COUNT];

    for (int i = 0; i < SIN_COUNT; i++) {
      sin[i] = (float) Math.sin((i + 0.5f) / SIN_COUNT * radFull);
      cos[i] = (float) Math.cos((i + 0.5f) / SIN_COUNT * radFull);
    }
  }

  public static final float atan2(float y, float x) {
    float add, mul;

    if (x < 0.0f) {
      if (y < 0.0f) {
        x = -x;
        y = -y;

        mul = 1.0f;
      } else {
        x = -x;
        mul = -1.0f;
      }

      add = -3.141592653f;
    } else {
      if (y < 0.0f) {
        y = -y;
        mul = -1.0f;
      } else {
        mul = 1.0f;
      }

      add = 0.0f;
    }

    float invDiv = ATAN2_DIM_MINUS_1 / ((x < y) ? y : x);

    // int xi = (int) (x * invDiv);
    // int yi = (int) (y * invDiv);

    // potential solution for index out of bounds
    int xi = Math.min((int) (x * invDiv), ATAN2_DIM);
    int yi = Math.min((int) (y * invDiv), ATAN2_DIM);

    return (atan2[yi * ATAN2_DIM + xi] + add) * mul;
  }

  private static final int ATAN2_BITS = 7;

  private static final int ATAN2_BITS2 = ATAN2_BITS << 1;
  private static final int ATAN2_MASK = ~(-1 << ATAN2_BITS2);
  private static final int ATAN2_COUNT = ATAN2_MASK + 1;
  private static final int ATAN2_DIM = (int) Math.sqrt(ATAN2_COUNT);

  private static final float ATAN2_DIM_MINUS_1 = (ATAN2_DIM - 1);

  private static final float[] atan2 = new float[ATAN2_COUNT];

  static {
    for (int i = 0; i < ATAN2_DIM; i++) {
      for (int j = 0; j < ATAN2_DIM; j++) {
        float x0 = (float) i / ATAN2_DIM;
        float y0 = (float) j / ATAN2_DIM;

        atan2[j * ATAN2_DIM + i] = (float) Math.atan2(y0, x0);
      }
    }
  }
}
