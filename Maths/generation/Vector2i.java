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
 */
package Maths.generation;

/**
 * {@code Vector2i} defines a vector for a two int value tuple. Utility methods
 * are also included to aid in mathematical calculations.
 * 
 * @author Jason Taylor
 */
public class Vector2i {

  public static final Vector2i ZERO = new Vector2i(0);
  public static final Vector2i UNIT_X = new Vector2i(1, 0);
  public static final Vector2i UNIT_Y = new Vector2i(0, 1);
  public static final Vector2i UNIT_XY = new Vector2i(1);
  public static final Vector2i MAX = new Vector2i(Integer.MAX_VALUE);
  public static final Vector2i MIN = new Vector2i(Integer.MIN_VALUE);

  /**
   * The X value of the vector
   */
  public int x;

  /**
   * The Y value of the vector
   */
  public int y;

  /**
   * Constructor instantiates a new {@code Vector3i} with default values of
   * (0,0,0)
   */
  public Vector2i() {
    x = y = 0;
  }

  public Vector2i(Vector2i v) {
    set(v);
  }

  public Vector2i(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Vector2i(int value) {
    x = y = value;
  }

  /**
   * Returns the distance between this {@code Vector3i} and {@code v}
   * 
   * @param v
   * @return
   */
  public float getDistance(Vector2i v) {
    return Vector2i.getDistance(this, v);
  }

  public static float getDistance(Vector2i v1, Vector2i v2) {
    float dx = v2.x - v1.x;
    float dy = v2.y - v1.y;
    return FastMath.sqrt(dx * dx + dy * dy);
  }

  /**
   * Returns the distance squared between this {@code Vector3i} and {@code v}
   * 
   * @param v
   * @return
   */
  public float getDistanceSquared(Vector2i v) {
    return Vector2i.getDistanceSquared(this, v);
  }

  public static float getDistanceSquared(Vector2i v1, Vector2i v2) {
    float dx = v2.x - v1.x;
    float dy = v2.y - v1.y;
    return dx * dx + dy * dy;
  }

  public Vector2i set(int x, int y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Vector2i set(Vector2i v) {
    x = v.x;
    y = v.y;
    return this;
  }

  public Vector2i set(Vector3f v) {
    x = (int) v.x;
    y = (int) v.y;
    return this;
  }

  public Vector2i multLocal(int i) {
    x *= i;
    y *= i;
    return this;
  }

  public Vector2i multLocal(Vector2i v) {
    x *= v.x;
    y *= v.y;
    return this;
  }

  public void add(Vector2i v, Vector2i target) {
    target.set(this);
    target.x += v.x;
    target.y += v.y;
  }

  public Vector2i addLocal(int i) {
    x += i;
    y += i;
    return this;
  }

  public Vector2i addLocal(Vector2i v) {
    x += v.x;
    y += v.y;
    return this;
  }

  public Vector2i subtractLocal(int i) {
    x -= i;
    y -= i;
    return this;
  }

  public Vector2i subtractLocal(Vector2i v) {
    x -= v.x;
    y -= v.y;
    return this;
  }

  /**
   * {@code length} calculates the magnitude of this vector.
   * 
   * @return the length or magnitude of the vector.
   */
  public float length() {
    return FastMath.sqrt(lengthSquared());
  }

  /**
   * {@code lengthSquared} calculates the squared value of the magnitude of the
   * vector.
   * 
   * @return the magnitude squared of the vector.
   */
  public int lengthSquared() {
    return x * x + y * y;
  }

  @Override
  public int hashCode() {
    int hash = 37;
    hash += 37 * hash + this.x;
    hash += 37 * hash + this.y;
    return hash;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (o == this)
      return true;
    if (o.getClass() != getClass())
      return false;
    Vector2i v = (Vector2i) o;
    return v.x == this.x && v.y == this.y;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + x + ", " + y + ")";
  }

}
