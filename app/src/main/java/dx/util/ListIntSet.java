/*
 * Copyright (C) 2008 The Android Open Source Project
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
 */

package dx.util;

import androidx.annotation.NonNull;

import java.util.NoSuchElementException;

/**
 * A set of integers, represented by a list
 */
public class ListIntSet implements IntSet {

    /** also accessed in BitIntSet */
    final IntList ints;

    /**
     * Constructs an instance
     */
    public ListIntSet() {
        ints = new IntList();
        ints.sort();
    }

    /** @inheritDoc */
    public void add(int value) {
        int index = ints.binarysearch(value);

        if (index < 0) {
            ints.insert(-(index + 1), value);
        }
    }

    /** @inheritDoc */
    public void remove(int value) {
        int index = ints.indexOf(value);

        if (index >= 0) {
            ints.removeIndex(index);
        }
    }

    /** @inheritDoc */
    public boolean has(int value) {
        return ints.indexOf(value) >= 0;
    }

    /** @inheritDoc */
    public void merge(IntSet other) {
        if (other instanceof ListIntSet o) {
            int szThis = ints.size();
            int szOther = o.ints.size();

            int i = 0;
            int j = 0;

            while (j < szOther && i < szThis) {
                while (j < szOther && o.ints.get(j) < ints.get(i)) {
                    add(o.ints.get(j++));
                }
                if (j == szOther) {
                    break;
                }
                while (i < szThis && o.ints.get(j) >= ints.get(i)) {
                    i++;
                }
            }

            while (j < szOther) {
                add(o.ints.get(j++));
            }

            ints.sort();
        } else if (other instanceof BitIntSet o) {

            for (int i = 0; i >= 0; i = Bits.findFirst(o.bits, i + 1)) {
                ints.add(i);
            }
            ints.sort();
        } else {
            IntIterator iter = other.iterator();
            while (iter.hasNext()) {
                add(iter.next());
            }
        }
    }

    /** @inheritDoc */
    public int elements() {
        return ints.size();
    }

    /** @inheritDoc */
    public IntIterator iterator() {
        return new IntIterator() {
            private int idx = 0;

            /** @inheritDoc */
            public boolean hasNext() {
                return idx < ints.size();
            }

            /** @inheritDoc */
            public int next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return ints.get(idx++);
            }
        };
    }

    /** @inheritDoc */
    @NonNull
    public String toString() {
        return ints.toString();
    }
}
