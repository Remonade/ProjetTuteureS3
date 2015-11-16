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
 * This class contains code from jMonkeyEngine, copyright jMonkeyEngine and 
 * licensed under New BSD License.
 * 
 * =\/==========================================================================
 * 
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package Maths.generation;

import java.util.Random;

/**
 * {@code FastMath} provides 'fast' math approximations and float equivalents of
 * Math functions. These are all used as static values and functions.
 * 
 * @author Various (original for JME)
 * @author Jason Taylor (modified)
 * 
 */
public final class FastMath {

  private FastMath() {
    //
  }

  /**
   * A close to zero double epsilon value
   */
  public static final double DBL_EPSILON = 2.220446049250313E-16d;

  /**
   * A close to zero float epsilon value
   */
  public static final float FLT_EPSILON = 1.1920928955078125E-7f;

  /**
   * A close to one float value
   */
  public static final float FLT_ONE_MINUS_EPSILON = 1.0f - FLT_EPSILON;

  /**
   * For use as zero tolerance value
   */
  public static final float ZERO_TOLERANCE = 0.0001f;

  /**
   * One divided by three floating point value
   */
  public static final float ONE_THIRD = 1.0f / 3.0f;

  /**
   * E as a floating point value
   */
  public static final float E = (float) Math.E;

  /**
   * PI as a floating point value
   */
  public static final float PI = (float) Math.PI;

  /**
   * 2PI as a floating point value
   */
  public static final float TWO_PI = 2.0f * PI;

  /**
   * PI/2 as a floating point value
   */
  public static final float HALF_PI = 0.5f * PI;

  /**
   * PI/4 as a floating point value
   */
  public static final float QUARTER_PI = 0.25f * PI;

  /**
   * 3PI/4 as a floating point value
   */
  public static final float THREE_QUARTER_PI = 0.75f * PI;

  /**
   * 1/PI as a floating point value
   */
  public static final float INV_PI = 1.0f / PI;

  /**
   * 1/(2PI) as a floating point value
   */
  public static final float INV_TWO_PI = 1.0f / TWO_PI;

  /**
   * PI/360 as a floating point value
   */
  public static final float PI_OVER_360 = PI / 360.0f;

  /**
   * Multiply by to convert from degrees to radians
   */
  public static final float DEG_TO_RAD = PI / 180.0f;

  /**
   * Multiply by to convert from radians to degrees
   */
  public static final float RAD_TO_DEG = 180.0f / PI;

  /**
   * Square root of 3 as a floating point value
   */
  public static final float SQRT3 = 1.7320508075688772935274463415059f;

  /**
   * Pre-generated random object
   */
  public static final Random rand = new Random(System.currentTimeMillis());

  // Used for fastFloor, fastRound, fastCeil
  // http://riven8192.blogspot.com/2010/02/fastmath-fast-floor.html
  private static final int BIG_ENOUGH_INT = 16 * 1024;
  private static final double BIG_ENOUGH_FLOOR = BIG_ENOUGH_INT + 0.0000;
  private static final double BIG_ENOUGH_ROUND = BIG_ENOUGH_INT + 0.5000;
  private static final double BIG_ENOUGH_CEIL = BIG_ENOUGH_INT + 0.9999;

  /**
   * Fast version of {@link FastMath#floor(float)}, faster than
   * {@link Math#floor(double)}.
   * <p>
   * http://riven8192.blogspot.com/2010/02/fastmath-fast-floor.html
   * 
   * @param x
   *          the float to floor
   * @return the floored float
   */
  public static int fastFloor(float x) {
    return (int) (x + BIG_ENOUGH_FLOOR) - BIG_ENOUGH_INT;
  }

  /**
   * Fast version of {@link Math#round(float)}.
   * <p>
   * http://riven8192.blogspot.com/2010/02/fastmath-fast-floor.html
   * 
   * @param x
   *          the float to round
   * @return the rounded float
   */
  public static int fastRound(float x) {
    return (int) (x + BIG_ENOUGH_ROUND) - BIG_ENOUGH_INT;
  }

  /**
   * Fast version of {@link FastMath#ceil(float)}, faster than
   * {@link Math#ceil(double)}.
   * <p>
   * http://riven8192.blogspot.com/2010/02/fastmath-fast-floor.html
   * 
   * @param x
   *          the float to ceil
   * @return the ceil'd float
   */
  public static int fastCeil(float x) {
    return (int) (x + BIG_ENOUGH_CEIL) - BIG_ENOUGH_INT;
  }

  /**
   * If the absolute value of supplied value is less than
   * {@link #ZERO_TOLERANCE}, zero is returned; otherwise, value is returned.
   * 
   * @param value
   * @return
   */
  public static float zeroTolerance(float value) {
    if (value == 0) {
      return 0;
    } else if (value < 0) {
      if (value > -ZERO_TOLERANCE) {
        return 0;
      }
    } else if (value > 0) {
      if (value < ZERO_TOLERANCE) {
        return 0;
      }
    }
    return value;
  }

  /**
   * Round {@code value} to the nearest 0.5f
   * 
   * @param value
   * @return
   */
  public static float roundToHalf(float value) {
    return ceil(value * 2.0f) / 2.0f;
  }

  /**
   * Returns true if the supplied integer {@code value} is a power of two
   * 
   * @param value
   * @return true if {@code value} is a power of two
   */
  public static boolean isPowerOfTwo(int value) {
    return (value > 0) && (value & (value - 1)) == 0;
  }

  /**
   * Returns an int power of two nearest the supplied integer {@code value}
   * 
   * @param value
   * @return power of two nearest {@code value}
   */
  public static int nearestPowerOfTwo(int value) {
    return (int) Math.pow(2, Math.ceil(Math.log(value) / Math.log(2)));
  }

  /**
   * Basic step interpolation between values y1 and y2; 0<=t<=1
   * 
   * @param t
   * @param y1
   * @param y2
   * @return
   */
  public static float interpolateStep(float t, float y1, float y2) {
    return (t == 1) ? y2 : y1;
  }

  /**
   * Linear interpolation between values {@code y1} and {@code y2} using time
   * {@code t} between time {@code x1} and time {@code x2}
   * 
   * @param t
   *          current time
   * @param x1
   *          start time
   * @param y1
   *          start value
   * @param x2
   *          end time
   * @param y2
   *          end value
   * @return
   */
  public static float interpolateLinear(float t, float x1, float y1, float x2, float y2) {
    return y1 + ((y2 - y1) / (x2 - x1)) * (t - x1);
  }

  /**
   * Linear interpolation between values {@code y1} and {@code y2}; 0<=t<=1
   * 
   * @param t
   *          current time percent [0.0f, 1.0f]
   * @param y1
   *          start value
   * @param y2
   *          end value
   * @return
   */
  public static float interpolateLinear(float t, float y1, float y2) {
    if (y1 == y2)
      return y1;
    if (t <= 0.0f)
      return y1;
    if (t >= 1.0f)
      return y2;
    return (y1 * (1 - t)) + (y2 * t);
  }

  /**
   * Linear interpolation from startValue to endValue by the given percent.
   * Basically: ((1 - percent) * startValue) + (percent * endValue)
   * 
   * @param scale
   *          scale value to use. if 1, use endValue, if 0, use startValue.
   * @param startValue
   *          Begining value. 0% of f
   * @param endValue
   *          ending value. 100% of f
   * @param store
   *          a vector3f to store the result
   * @return The interpolated value between startValue and endValue.
   */
  public static Vector3f interpolateLinear(float scale, Vector3f startValue, Vector3f endValue, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    store.x = interpolateLinear(scale, startValue.x, endValue.x);
    store.y = interpolateLinear(scale, startValue.y, endValue.y);
    store.z = interpolateLinear(scale, startValue.z, endValue.z);
    return store;
  }

  /**
   * Linear interpolation from startValue to endValue by the given percent.
   * Basically: ((1 - percent) * startValue) + (percent * endValue)
   * 
   * @param scale
   *          scale value to use. if 1, use endValue, if 0, use startValue.
   * @param startValue
   *          Begining value. 0% of f
   * @param endValue
   *          ending value. 100% of f
   * @return The interpolated value between startValue and endValue.
   */
  public static Vector3f interpolateLinear(float scale, Vector3f startValue, Vector3f endValue) {
    return interpolateLinear(scale, startValue, endValue, null);
  }

  /**
   * Linear extrapolation from startValue to endValue by the given scale. if
   * scale is between 0 and 1 this method returns the same result as
   * interpolateLinear if the scale is over 1 the value is linearly
   * extrapolated. Note that the end value is the value for a scale of 1.
   * 
   * @param scale
   *          the scale for extrapolation
   * @param startValue
   *          the starting value (scale = 0)
   * @param endValue
   *          the end value (scale = 1)
   * @return an extrapolation for the given parameters
   */
  public static float extrapolateLinear(float scale, float startValue, float endValue) {
    return ((1f - scale) * startValue) + (scale * endValue);
  }

  /**
   * Linear extrapolation from startValue to endValue by the given scale. if
   * scale is between 0 and 1 this method returns the same result as
   * interpolateLinear if the scale is over 1 the value is linearly
   * extrapolated. Note that the end value is the value for a scale of 1.
   * 
   * @param scale
   *          the scale for extrapolation
   * @param startValue
   *          the starting value (scale = 0)
   * @param endValue
   *          the end value (scale = 1)
   * @param store
   *          an initialized vector to store the return value
   * @return an extrapolation for the given parameters
   */
  public static Vector3f extrapolateLinear(float scale, Vector3f startValue, Vector3f endValue, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    store.x = extrapolateLinear(scale, startValue.x, endValue.x);
    store.y = extrapolateLinear(scale, startValue.y, endValue.y);
    store.z = extrapolateLinear(scale, startValue.z, endValue.z);
    return store;
  }

  /**
   * Linear extrapolation from startValue to endValue by the given scale. if
   * scale is between 0 and 1 this method returns the same result as
   * interpolateLinear if the scale is over 1 the value is linearly
   * extrapolated. Note that the end value is the value for a scale of 1.
   * 
   * @param scale
   *          the scale for extrapolation
   * @param startValue
   *          the starting value (scale = 0)
   * @param endValue
   *          the end value (scale = 1)
   * @return an extrapolation for the given parameters
   */
  public static Vector3f extrapolateLinear(float scale, Vector3f startValue, Vector3f endValue) {
    return extrapolateLinear(scale, startValue, endValue, null);
  }

  /**
   * Interpolate a spline between at least 4 control points following the
   * Catmull-Rom equation
   * 
   * @param u
   *          value from 0 to 1
   * @param T
   *          The tension of the curve
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @return catmull-Rom interpolation
   */
  public static float interpolateCatmullRom(float u, float T, float p0, float p1, float p2, float p3) {
    float c1, c2, c3, c4;
    c1 = p1;
    c2 = -1.0f * T * p0 + T * p2;
    c3 = 2 * T * p0 + (T - 3) * p1 + (3 - 2 * T) * p2 + -T * p3;
    c4 = -T * p0 + (2 - T) * p1 + (T - 2) * p2 + T * p3;

    return (float) (((c4 * u + c3) * u + c2) * u + c1);
  }

  /**
   * Interpolate a spline between at least 4 control points following the
   * Catmull-Rom equation.
   * 
   * @param u
   *          value from 0 to 1
   * @param T
   *          The tension of the curve
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @param store
   *          a Vector3f to store the result
   * @return catmull-Rom interpolation
   */
  public static Vector3f interpolateCatmullRom(float u, float T, Vector3f p0, Vector3f p1, Vector3f p2, Vector3f p3, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    store.x = interpolateCatmullRom(u, T, p0.x, p1.x, p2.x, p3.x);
    store.y = interpolateCatmullRom(u, T, p0.y, p1.y, p2.y, p3.y);
    store.z = interpolateCatmullRom(u, T, p0.z, p1.z, p2.z, p3.z);
    return store;
  }

  /**
   * Compute the lenght on a catmull rom spline between control point 1 and 2
   * 
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @param startRange
   *          the starting range on the segment (use 0)
   * @param endRange
   *          the end range on the segment (use 1)
   * @param curveTension
   *          the curve tension
   * @return the length of the segment
   */
  public static float getCatmullRomP1toP2Length(Vector3f p0, Vector3f p1, Vector3f p2, Vector3f p3, float startRange, float endRange, float curveTension) {

    float epsilon = 0.001f;
    float middleValue = (startRange + endRange) * 0.5f;
    Vector3f start = p1.clone();
    if (startRange != 0) {
      FastMath.interpolateCatmullRom(startRange, curveTension, p0, p1, p2, p3, start);
    }
    Vector3f end = p2.clone();
    if (endRange != 1) {
      FastMath.interpolateCatmullRom(endRange, curveTension, p0, p1, p2, p3, end);
    }
    Vector3f middle = FastMath.interpolateCatmullRom(middleValue, curveTension, p0, p1, p2, p3);
    float l = end.subtract(start).length();
    float l1 = middle.subtract(start).length();
    float l2 = end.subtract(middle).length();
    float len = l1 + l2;
    if (l + epsilon < len) {
      l1 = getCatmullRomP1toP2Length(p0, p1, p2, p3, startRange, middleValue, curveTension);
      l2 = getCatmullRomP1toP2Length(p0, p1, p2, p3, middleValue, endRange, curveTension);
    }
    l = l1 + l2;
    return l;
  }

  /**
   * Interpolate a spline between at least 4 control points following the
   * Catmull-Rom equation.
   * 
   * @param u
   *          value from 0 to 1
   * @param T
   *          The tension of the curve
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @return catmull-Rom interpolation
   */
  public static Vector3f interpolateCatmullRom(float u, float T, Vector3f p0, Vector3f p1, Vector3f p2, Vector3f p3) {
    return interpolateCatmullRom(u, T, p0, p1, p2, p3, null);
  }

  /**
   * Interpolate a spline between at least 4 control points following the Bezier
   * equation.
   * 
   * @param u
   *          value from 0 to 1
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @return Bezier interpolation
   */
  public static float interpolateBezier(float u, float p0, float p1, float p2, float p3) {
    float oneMinusU = 1.0f - u;
    float oneMinusU2 = oneMinusU * oneMinusU;
    float u2 = u * u;
    return p0 * oneMinusU2 * oneMinusU + 3.0f * p1 * u * oneMinusU2 + 3.0f * p2 * u2 * oneMinusU + p3 * u2 * u;
  }

  /**
   * Interpolate a spline between at least 4 control points following the Bezier
   * equation.
   * 
   * @param u
   *          value from 0 to 1
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @param store
   *          a Vector3f to store the result
   * @return Bezier interpolation
   */
  public static Vector3f interpolateBezier(float u, Vector3f p0, Vector3f p1, Vector3f p2, Vector3f p3, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    store.x = interpolateBezier(u, p0.x, p1.x, p2.x, p3.x);
    store.y = interpolateBezier(u, p0.y, p1.y, p2.y, p3.y);
    store.z = interpolateBezier(u, p0.z, p1.z, p2.z, p3.z);
    return store;
  }

  /**
   * Interpolate a spline between at least 4 control points following the Bezier
   * equation.
   * 
   * @param u
   *          value from 0 to 1
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @return Bezier interpolation
   */
  public static Vector3f interpolateBezier(float u, Vector3f p0, Vector3f p1, Vector3f p2, Vector3f p3) {
    return interpolateBezier(u, p0, p1, p2, p3, null);
  }

  /**
   * Compute the lenght on a bezier spline between control point 1 and 2
   * 
   * @param p0
   *          control point 0
   * @param p1
   *          control point 1
   * @param p2
   *          control point 2
   * @param p3
   *          control point 3
   * @return the length of the segment
   */
  public static float getBezierP1toP2Length(Vector3f p0, Vector3f p1, Vector3f p2, Vector3f p3) {
    float delta = 0.02f, t = 0.0f, result = 0.0f;
    Vector3f v1 = p0.clone(), v2 = new Vector3f();
    while (t <= 1.0f) {
      FastMath.interpolateBezier(t, p0, p1, p2, p3, v2);
      result += v1.subtractLocal(v2).length();
      v1.set(v2);
      t += delta;
    }
    return result;
  }

  /**
   * 
   * @param t
   * @param y1
   * @param y2
   * @return
   */
  public static float interpolateCosine(float t, float y1, float y2) {
    float f = (1 - cos(t * PI)) * 0.5f;
    return y1 * (1 - f) + y2 * f;
  }

  /**
   * 
   * @param t
   * @param y1
   * @param y2
   * @return
   */
  public static float interpolateCosineLUT(float t, float y1, float y2) {
    float f = (1 - TrigLUT.cos(t * PI)) * 0.5f;
    return y1 * (1 - f) + y2 * f;
  }

  /**
   * Returns the arc cosine of {@code value}<br>
   * Special cases:
   * <ul>
   * <li>If {@code value} is smaller than -1, then the result is PI.
   * <li>If the {@code value} is greater than 1, then the result is 0.
   * </ul>
   * 
   * @param value
   *          The value to arc cosine
   * @return The angle, in radians
   * @see java.lang.Math#acos(double)
   */
  public static float acos(float value) {
    if (-1.0f < value) {
      if (value < 1.0f) {
        return (float) Math.acos(value);
      }
      return 0.0f;
    }
    return PI;
  }

  /**
   * Returns the arc sine of a {@code value}<br>
   * Special cases:
   * <ul>
   * <li>If {@code value} is smaller than -1, then the result is -HALF_PI.
   * <li>If {@code value} is greater than 1, then the result is HALF_PI.
   * </ul>
   * 
   * @param value
   *          The value to arc sine
   * @return the angle in radians
   * @see java.lang.Math#asin(double)
   */
  public static float asin(float value) {
    if (-1.0f < value) {
      if (value < 1.0f) {
        return (float) Math.asin(value);
      }
      return HALF_PI;
    }
    return -HALF_PI;
  }

  /**
   * Returns the arc tangent of an angle ({@code value}) given in radians;
   * direct call to java.lang.Math
   * 
   * @param value
   *          The angle, in radians
   * @return value's atan
   * @see java.lang.Math#atan(double)
   */
  public static float atan(float value) {
    return (float) Math.atan(value);
  }

  /**
   * A direct call to Math.atan2
   * 
   * @param y
   * @param x
   * @return Math.atan2(y, x)
   * @see java.lang.Math#atan2(double, double)
   */
  public static float atan2(float y, float x) {
    return (float) Math.atan2(y, x);
  }

  /**
   * Approximates atan2 with a lookup table
   * 
   * @param y
   * @param x
   * @return
   */
  public static float atan2LUT(float y, float x) {
    return TrigLUT.atan2(y, x);
  }

  /**
   * Rounds {@code value} up; direct call to java.lang.Math
   * 
   * @param value
   *          The value
   * @return The value rounded up
   * @see java.lang.Math#ceil(double)
   */
  public static float ceil(float value) {
    return (float) Math.ceil(value);
  }

  /**
   * Returns cosine of an angle ({@code value}); direct call to java.lang.Math
   * 
   * @see Math#cos(double)
   * @param value
   *          the angle to cosine in radians
   * @return the cosine of the angle
   */
  public static float cos(float value) {
    return (float) Math.cos(value);
  }

  /**
   * Returns the approximate cosine of an angle using a lookup table.
   * 
   * @param value
   *          the angle to cosine in radians
   * @return the approximate cosine of the angle
   */
  public static float cosLUT(float value) {
    return TrigLUT.cos(value);
  }

  /**
   * Returns the sine of an angle ({@code value}); direct call to java.lang.Math
   * 
   * @see Math#sin(double)
   * @param value
   *          The angle to sine
   * @return the sine of the angle
   */
  public static float sin(float value) {
    return (float) Math.sin(value);
  }

  /**
   * Returns the approximate sine of an angle using a lookup table.
   * 
   * @param value
   *          the angle to sine in radians
   * @return the approximate sine of the angle
   */
  public static float sinLUT(float value) {
    return TrigLUT.sin(value);
  }

  /**
   * Returns Euler's number E raised to the power of {@code value}; direct call
   * to java.lang.Math
   * 
   * @param value
   *          Value to raise to a power
   * @return The value E^value
   * @see java.lang.Math#exp(double)
   */
  public static float exp(float value) {
    return (float) Math.exp(value);
  }

  /**
   * Returns Absolute value of {@code value}
   * 
   * @param value
   *          The value to abs
   * @return The abs of the value
   * @see java.lang.Math#abs(float)
   */
  public static float abs(float value) {
    if (value < 0) {
      return -value;
    }
    return value;
  }

  public static int abs(int value) {
    if (value < 0) {
      return -value;
    }
    return value;
  }

  /**
   * Returns {@code value} rounded down; direct call to java.lang.Math
   * 
   * @param value
   *          The value to round
   * @return The given number rounded down
   * @see java.lang.Math#floor(double)
   */
  public static float floor(float value) {
    return (float) Math.floor(value);
  }

  public static int floor(int value) {
    return (int) Math.floor(value);
  }

  /**
   * Returns {@code value} squared
   * 
   * @param value
   *          The value to square
   * @return The square of the given value
   */
  public static float sqr(float value) {
    return value * value;
  }

  /**
   * Returns the square root of {@code value}; direct call to java.lang.Math
   * 
   * @param value
   *          The value to sqrt.
   * @return The square root of the given value.
   * @see java.lang.Math#sqrt(double)
   */
  public static float sqrt(float value) {
    return (float) Math.sqrt(value);
  }

  /**
   * Returns the cube root of {@code value}; direct call to java.lang.Math
   * 
   * @param value
   *          the value to cube root
   * @return the cube root of the given value
   * @see java.lang.Math#cbrt(double)
   */
  public static float cbrt(float value) {
    return (float) Math.cbrt(value);
  }

  /**
   * Returns 1/sqrt({@code value}); uses call to java.lang.Math
   * 
   * @param value
   *          The value to process.
   * @return 1/sqrt({@code value})
   * @see java.lang.Math#sqrt(double)
   */
  public static float invSqrt(float value) {
    return (float) (1.0f / Math.sqrt(value));
  }

  /**
   * Uses bit hacking to approximate 1/sqrt({@code value})
   * 
   * @param x
   * @return an approximation of 1/sqrt({@code value})
   * @see <a
   *      href="http://en.wikipedia.org/wiki/Fast_inverse_square_root">http://en.wikipedia.org/wiki/Fast_inverse_square_root</a>
   */
  public static float fastInvSqrt(float x) {
    float xhalf = 0.5f * x;
    int i = Float.floatToIntBits(x);
    i = 0x5f375a86 - (i >> 1);
    x = Float.intBitsToFloat(i);
    // repeat the following Newton step for more accuracy
    x = x * (1.5f - (xhalf * x * x));
    return x;
  }

  /**
   * Returns the log base E of {@code value}; direct call to java.lang.Math
   * 
   * @param value
   *          The value to log
   * @return The log of value base E
   * @see java.lang.Math#log(double)
   */
  public static float log(float value) {
    return (float) Math.log(value);
  }

  /**
   * Returns the logarithm of {@code value} with given {@code base}<br>
   * <br>
   * calculated as log({@code value}) / log({@code base})<br>
   * so that pow({@code base} , {@code return}) == {@code value}<br>
   * <br>
   * uses direct calls to java.lang.Math
   * 
   * @param value
   *          The value to log
   * @param base
   *          Base of logarithm
   * @return The logarithm of value with given base
   */
  public static float log(float value, float base) {
    return (float) (Math.log(value) / Math.log(base));
  }

  /**
   * Returns {@code base} raised to {@code exp}; direct call to java.lang.Math
   * 
   * @param base
   *          The base value
   * @param exp
   *          The exponent value
   * @return base raised to exponent
   * @see java.lang.Math#pow(double, double)
   */
  public static float pow(float base, float exp) {
    return (float) Math.pow(base, exp);
  }

  /**
   * Returns the tangent of {@code value}; direct call to java.lang.Math
   * 
   * @param value
   *          The value to tangent, in radians
   * @return The tangent of value
   * @see java.lang.Math#tan(double)
   */
  public static float tan(float value) {
    return (float) Math.tan(value);
  }

  /**
   * Takes a value and expresses it in terms of min to max.
   * 
   * @param val
   *          the value to normalize
   * @return the normalized value
   */
  public static float normalize(float val, float min, float max) {
    if (Float.isInfinite(val) || Float.isNaN(val)) {
      return 0f;
    }
    float range = max - min;
    while (val > max) {
      val -= range;
    }
    while (val < min) {
      val += range;
    }
    return val;
  }

  /**
   * Returns 1 if {@code value} is positive, -1 if {@code value} is negative,
   * and 0 otherwise
   * 
   * @param value
   *          The integer to examine
   * @return The integer's sign
   */
  public static int sign(int value) {
    if (value > 0)
      return 1;
    if (value < 0)
      return -1;
    return 0;
  }

  /**
   * Returns 1 if {@code value} is positive, -1 if {@code value} is negative,
   * and 0 otherwise; direct call to java.lang.Math
   * 
   * @param value
   *          The float to examine
   * @return The float's sign
   * @see java.lang.Math#signum(float)
   */
  public static float sign(float value) {
    return Math.signum(value);
  }

  /**
   * Returns a random float between 0 and 1
   * 
   * @return A random float between {@code 0.0f} (inclusive) and {@code 1.0f}
   *         (exclusive)
   */
  public static float nextRandomFloat() {
    return rand.nextFloat();
  }

  /**
   * Returns a random integer between {@code min} (inclusive) and {@code max}
   * (inclusive)
   * 
   * @param min
   *          minimum value
   * @param max
   *          maximum value
   * @return A random int between {@code min} (inclusive) to {@code max}
   *         (inclusive)
   */
  public static int nextRandomInt(int min, int max) {
    return (int) (nextRandomFloat() * (max - min + 1)) + min;
  }

  public static int nextRandomInt(Random rand, int min, int max) {
    return (int) (rand.nextFloat() * (max - min + 1)) + min;
  }

  /**
   * Returns a random integer
   * 
   * @return random integer
   * @see java.util.Random#nextInt(int)
   */
  public static int nextRandomInt() {
    return rand.nextInt();
  }

  /**
   * Returns a random long
   * 
   * @return random long
   * @see java.util.Random#nextLong()
   */
  public static long nextRandomLong() {
    return rand.nextLong();
  }

  /**
   * Adjust sign of {@code x} to match sign of {@code y}
   * 
   * @param x
   *          the value whose sign is to be adjusted
   * @param y
   *          the value whose sign is to be used
   * @return x with its sign changed to match the sign of y
   */
  public static float copysign(float x, float y) {
    if (y >= 0 && x <= -0) {
      return -x;
    } else if (y < 0 && x >= 0) {
      return -x;
    } else {
      return x;
    }
  }

  /**
   * Take a float {@code input} and clamp it between {@code min} and {@code max}
   * 
   * @param input
   * @param min
   * @param max
   * @return clamped input
   */
  public static float clamp(float input, float min, float max) {
    return (input < min) ? min : (input > max) ? max : input;
  }

  public static int clamp(int input, int min, int max) {
    return (input < min) ? min : (input > max) ? max : input;
  }

  /**
   * Clamps {@code input} to between 0 and 1
   * 
   * @param input
   * @return input clamped between 0 and 1
   */
  public static float saturate(float input) {
    return clamp(input, 0.0f, 1.0f);
  }

  /**
   * Converts a half precision (16 bit) floating point value into single
   * precision (32 bit).
   * 
   * @param half
   *          The half floating point value as a short.
   * @return floating point value of the half.
   */
  public static float convertHalfToFloat(short half) {
    switch ((int) half) {
    case 0x0000:
      return 0f;
    case 0x8000:
      return -0f;
    case 0x7c00:
      return Float.POSITIVE_INFINITY;
    case 0xfc00:
      return Float.NEGATIVE_INFINITY;
      // TODO: Support for NaN?
    default:
      return Float.intBitsToFloat(((half & 0x8000) << 16) | (((half & 0x7c00) + 0x1C000) << 13) | ((half & 0x03FF) << 13));
    }
  }

  /**
   * Converts a single precision (32 bit) floating point value into half
   * precision (16 bit)
   * 
   * @param flt
   *          the floating point value
   * @return half precision floating point value as a short
   */
  public static short convertFloatToHalf(float flt) {
    if (Float.isNaN(flt)) {
      throw new UnsupportedOperationException("NaN to half conversion not supported!");
    } else if (flt == Float.POSITIVE_INFINITY) {
      return (short) 0x7c00;
    } else if (flt == Float.NEGATIVE_INFINITY) {
      return (short) 0xfc00;
    } else if (flt == 0f) {
      return (short) 0x0000;
    } else if (flt == -0f) {
      return (short) 0x8000;
    } else if (flt > 65504f) {
      // max value supported by half float
      return 0x7bff;
    } else if (flt < -65504f) {
      return (short) (0x7bff | 0x8000);
    } else if (flt > 0f && flt < 5.96046E-8f) {
      return 0x0001;
    } else if (flt < 0f && flt > -5.96046E-8f) {
      return (short) 0x8001;
    }

    int f = Float.floatToIntBits(flt);
    return (short) (((f >> 16) & 0x8000) | ((((f & 0x7f800000) - 0x38000000) >> 13) & 0x7c00) | ((f >> 13) & 0x03ff));
  }

  /**
   * Converts a point from Spherical coordinates to Cartesian (using positive Y
   * as up) and stores the results in the store var. If {@code store} is null, a
   * new {@code Vector3f} containing the result is returned.
   * <ul>
   * <li>{@code sphereCoords.x = radius}</li>
   * <li>{@code sphereCoords.y = azimuth}</li>
   * <li>{@code sphereCoords.z = inclination} (elevation from reference plane)</li>
   * </ul>
   * 
   * @param sphereCoords
   *          a {@code Vector3f} containing the spherical coordinates
   * @param store
   *          the {@code Vector3f} to store the result in
   * @see <a href=http://en.wikipedia.org/wiki/Spherical_coordinate_system#
   *      Coordinate_system_conversions
   *      >http://en.wikipedia.org/wiki/Spherical_coordinate_system
   *      #Coordinate_system_conversions</a>
   * @see FastMath#sphericalToCartesian(float, float, float, Vector3f)
   */
  public static Vector3f sphericalToCartesian(Vector3f sphereCoords, Vector3f store) {
    return sphericalToCartesian(sphereCoords.x, sphereCoords.z, sphereCoords.y, store);
  }

  /**
   * Converts a point from Spherical coordinates to Cartesian (using positive Y
   * as up) and stores the results in the store var. If {@code store} is null, a
   * new {@code Vector3f} containing the result is returned.
   * 
   * @see <a href=http://en.wikipedia.org/wiki/Spherical_coordinate_system#
   *      Coordinate_system_conversions
   *      >http://en.wikipedia.org/wiki/Spherical_coordinate_system
   *      #Coordinate_system_conversions</a>
   */
  public static Vector3f sphericalToCartesian(float radius, float inclination, float azimuth, Vector3f store) {

    if (store == null)
      store = new Vector3f();

    store.y = radius * FastMath.sin(inclination);
    float a = radius * FastMath.cos(inclination);
    store.x = a * FastMath.cos(azimuth);
    store.z = a * FastMath.sin(azimuth);

    return store;
  }

  /**
   * Converts a point from Cartesian coordinates (using positive Y as up) to
   * Spherical and stores the results in the store var. (Radius, Azimuth, Polar)
   */
  public static Vector3f cartesianToSpherical(Vector3f cartCoords, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    float x = cartCoords.x;
    if (x == 0) {
      x = FastMath.FLT_EPSILON;
    }
    store.x = FastMath.sqrt((x * x) + (cartCoords.y * cartCoords.y) + (cartCoords.z * cartCoords.z));
    store.y = FastMath.atan(cartCoords.z / x);
    if (x < 0) {
      store.y += FastMath.PI;
    }
    store.z = FastMath.asin(cartCoords.y / store.x);
    return store;
  }

  /**
   * Converts a point from Spherical coordinates to Cartesian (using positive Z
   * as up) and stores the results in the store var.
   */
  public static Vector3f sphericalToCartesianZ(Vector3f sphereCoords, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    store.z = sphereCoords.x * FastMath.sin(sphereCoords.z);
    float a = sphereCoords.x * FastMath.cos(sphereCoords.z);
    store.x = a * FastMath.cos(sphereCoords.y);
    store.y = a * FastMath.sin(sphereCoords.y);

    return store;
  }

  /**
   * Converts a point from Cartesian coordinates (using positive Z as up) to
   * Spherical and stores the results in the store var. (Radius, Azimuth, Polar)
   */
  public static Vector3f cartesianZToSpherical(Vector3f cartCoords, Vector3f store) {
    if (store == null) {
      store = new Vector3f();
    }
    float x = cartCoords.x;
    if (x == 0) {
      x = FastMath.FLT_EPSILON;
    }
    store.x = FastMath.sqrt((x * x) + (cartCoords.y * cartCoords.y) + (cartCoords.z * cartCoords.z));
    store.z = FastMath.atan(cartCoords.z / x);
    if (x < 0) {
      store.z += FastMath.PI;
    }
    store.y = FastMath.asin(cartCoords.y / store.x);
    return store;
  }

  /**
   * Returns the determinant of a 4x4 matrix.
   */
  public static float determinant(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21,
      double m22, double m23, double m30, double m31, double m32, double m33) {

    double det01 = m20 * m31 - m21 * m30;
    double det02 = m20 * m32 - m22 * m30;
    double det03 = m20 * m33 - m23 * m30;
    double det12 = m21 * m32 - m22 * m31;
    double det13 = m21 * m33 - m23 * m31;
    double det23 = m22 * m33 - m23 * m32;
    return (float) (m00 * (m11 * det23 - m12 * det13 + m13 * det12) - m01 * (m10 * det23 - m12 * det03 + m13 * det02) + m02
        * (m10 * det13 - m11 * det03 + m13 * det01) - m03 * (m10 * det12 - m11 * det02 + m12 * det01));
  }

  /**
   * Given 3 points in a 2d plane, this function computes if the points going
   * from A-B-C are moving counter clock wise.
   * 
   * @param p0
   *          Point 0.
   * @param p1
   *          Point 1.
   * @param p2
   *          Point 2.
   * @return 1 If they are CCW, -1 if they are not CCW, 0 if p2 is between p0
   *         and p1.
   */
  public static int counterClockwise(Vector2f p0, Vector2f p1, Vector2f p2) {
    float dx1, dx2, dy1, dy2;
    dx1 = p1.x - p0.x;
    dy1 = p1.y - p0.y;
    dx2 = p2.x - p0.x;
    dy2 = p2.y - p0.y;
    if (dx1 * dy2 > dy1 * dx2) {
      return 1;
    }
    if (dx1 * dy2 < dy1 * dx2) {
      return -1;
    }
    if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0)) {
      return -1;
    }
    if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) {
      return 1;
    }
    return 0;
  }

  /**
   * Test if a point is inside a triangle. 1 if the point is on the ccw side, -1
   * if the point is on the cw side, and 0 if it is on neither.
   * 
   * @param t0
   *          First point of the triangle.
   * @param t1
   *          Second point of the triangle.
   * @param t2
   *          Third point of the triangle.
   * @param p
   *          The point to test.
   * @return Value 1 or -1 if inside triangle, 0 otherwise.
   */
  public static int pointInsideTriangle(Vector2f t0, Vector2f t1, Vector2f t2, Vector2f p) {
    int val1 = counterClockwise(t0, t1, p);
    if (val1 == 0) {
      return 1;
    }
    int val2 = counterClockwise(t1, t2, p);
    if (val2 == 0) {
      return 1;
    }
    if (val2 != val1) {
      return 0;
    }
    int val3 = counterClockwise(t2, t0, p);
    if (val3 == 0) {
      return 1;
    }
    if (val3 != val1) {
      return 0;
    }
    return val3;
  }

  public static Vector3f[] uniformPointsOnSphere(int n) {
    if (n < 1) {
      throw new IllegalArgumentException("number of points must be greater than 0");
    }

    float y, r, phi, inc, off, k, fn;

    fn = (float) n;
    Vector3f[] v = new Vector3f[n];
    inc = PI * (3.0f - sqrt(5.0f));
    off = 2.0f / fn;
    for (k = 0; k < fn; k++) {
      y = k * off - 1 + (off / 2.0f);
      r = sqrt(1 - y * y);
      phi = k * inc;
      v[(int) k] = new Vector3f(cos(phi) * r, y, sin(phi) * r);
    }

    return v;
  }

  public static Vector3f[] perturbedPointsOnSphere(int n, int seed, float factor) {
    if (n < 1) {
      throw new IllegalArgumentException("number of points must be greater than 0");
    }

    Random rand = new Random(seed);

    float y, r, phi, inc, off, k, fn;

    fn = (float) n;
    Vector3f[] v = new Vector3f[n];
    inc = PI * (3.0f - sqrt(5.0f));
    off = 2.0f / fn;
    for (k = 0; k < fn; k++) {
      y = k * off - 1 + (off / 2.0f);
      r = sqrt(1 - y * y);
      phi = k * inc + rand.nextFloat() * factor;
      v[(int) k] = new Vector3f(cos(phi) * r, y, sin(phi) * r);
    }

    return v;
  }

  /**
   * A method that computes normal for a triangle defined by three vertices.
   * 
   * @param v1
   *          first vertex
   * @param v2
   *          second vertex
   * @param v3
   *          third vertex
   * @return a normal for the face
   */
  public static Vector3f computeNormal(Vector3f v1, Vector3f v2, Vector3f v3) {
    Vector3f a1 = v1.subtract(v2);
    Vector3f a2 = v3.subtract(v2);
    return a2.crossLocal(a1).normalizeLocal();
  }

  /**
   * Converts polar coordinates to cartesian coordinates.
   * <p>
   * Warning: This method creates a new {@link Vector2f} for each call.
   * 
   * @param size
   * @param angle
   * @return
   */
  public static Vector2f polarToCartesian(float size, float angle) {
    return polarToCartesian(size, angle, null);
  }

  /**
   * Converts polar coordinates to cartesian coordinates and stores the results
   * in {@code store}.
   * <p>
   * If {@code store} is null a new {@link Vector2f} will be created.
   * 
   * @param size
   * @param angle
   * @param store
   * @return
   */
  public static Vector2f polarToCartesian(float size, float angle, Vector2f store) {
    if (store == null) {
      store = new Vector2f();
    }
    store.x = size * cos(angle);
    store.y = size * sin(angle);
    return store;
  }

  public static int max(int x, int y, int z) {
    return Math.max(x, Math.max(y, z));
  }

  public static float max(float x, float y, float z) {
    return Math.max(x, Math.max(y, z));
  }

  public static int min(int x, int y, int z) {
    return Math.min(x, Math.min(y, z));
  }

  public static float min(float x, float y, float z) {
    return Math.min(x, Math.min(y, z));
  }

  /**
   * Returns true if abs(a-b) < {@link #FLT_EPSILON}.
   * 
   * @param a
   * @param b
   * @return
   */
  public static boolean compareFloat(float a, float b) {
    return abs(a - b) < FLT_EPSILON;
  }

  /**
   * Returns true if abs(a-b) < precision.
   * 
   * @param a
   * @param b
   * @param precision
   * @return true or false
   */
  public static boolean compareFloat(float a, float b, float precision) {
    return abs(a - b) < precision;
  }

}
