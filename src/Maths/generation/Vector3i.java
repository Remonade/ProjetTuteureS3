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
 * {@code Vector3i} defines a vector for a three int value tuple. Utility
 * methods are also included to aid in mathematical calculations.
 *
 * @author Jason Taylor
 */
public class Vector3i {
    
    public static final Vector3i ZERO = new Vector3i(0);
    public static final Vector3i UNIT_X = new Vector3i(1, 0, 0);
    public static final Vector3i UNIT_Y = new Vector3i(0, 1, 0);
    public static final Vector3i UNIT_Z = new Vector3i(0, 0, 1);
    public static final Vector3i UNIT_XYZ = new Vector3i(1);
    public static final Vector3i MAX = new Vector3i(Integer.MAX_VALUE);
    public static final Vector3i MIN = new Vector3i(Integer.MIN_VALUE);
    
    /**
     * The X value of the vector
     */
    public int x;
    
    /**
     * The Y value of the vector
     */
    public int y;
    
    /**
     * The Z value of the vector
     */
    public int z;
    
    /**
     * Constructor instantiates a new {@code Vector3i} with default values of
     * (0,0,0)
     */
    public Vector3i() {
        x = y = z = 0;
    }
    
    public Vector3i(Vector3i v) {
        set(v);
    }
    
    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3i(int value) {
        x = y = z = value;
    }
    
    /**
     * Returns the distance between this {@code Vector3i} and {@code v}
     *
     * @param v
     * @return
     */
    public float getDistance(Vector3i v) {
        return Vector3i.getDistance(this, v);
    }
    
    public static float getDistance(Vector3i v1, Vector3i v2) {
        float dx = v2.x - v1.x;
        float dy = v2.y - v1.y;
        float dz = v2.z - v1.z;
        return FastMath.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    /**
     * Returns the distance squared between this {@code Vector3i} and {@code v}
     *
     * @param v
     * @return
     */
    public float getDistanceSquared(Vector3i v) {
        return Vector3i.getDistanceSquared(this, v);
    }
    
    public static float getDistanceSquared(Vector3i v1, Vector3i v2) {
        float dx = v2.x - v1.x;
        float dy = v2.y - v1.y;
        float dz = v2.z - v1.z;
        return dx * dx + dy * dy + dz * dz;
    }
    
    public Vector3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    
    public Vector3i set(Vector3i v) {
        x = v.x;
        y = v.y;
        z = v.z;
        return this;
    }
    
    public Vector3i set(Vector3f v) {
        x = (int) v.x;
        y = (int) v.y;
        z = (int) v.z;
        return this;
    }
    
    public Vector3i add(Vector3i v, Vector3i store) {
        store.set(this);
        store.x += v.x;
        store.y += v.y;
        store.z += v.z;
        return this;
    }
    
    public Vector3i addLocal(Vector3i v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }
    
    public Vector3i addLocal(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    public Vector3i multLocal(Vector3i v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;
        return this;
    }
    
    public Vector3i multLocal(int scale) {
        this.x *= scale;
        this.y *= scale;
        this.z *= scale;
        return this;
    }
    
    public Vector3i subtractLocal(Vector3i v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        return this;
    }
    
    @Override
    public int hashCode() {
        int hash = 37;
        hash += 37 * hash + this.x;
        hash += 37 * hash + this.y;
        hash += 37 * hash + this.z;
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
        Vector3i v = (Vector3i) o;
        return v.x == this.x && v.y == this.y && v.z == this.z;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    
}
