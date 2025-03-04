/*
 * Copyright (C) 2010 The Android Open Source Project
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

package dx.dex.code.form;

import java.util.BitSet;

import dx.dex.code.CstInsn;
import dx.dex.code.DalvInsn;
import dx.dex.code.InsnFormat;
import dx.rop.code.RegisterSpecList;
import dx.rop.cst.Constant;
import dx.rop.cst.CstFieldRef;
import dx.rop.cst.CstType;
import dx.util.AnnotatedOutput;

/**
 * Instruction format {@code 52c}. See the instruction format spec
 * for details.
 */
public final class Form52c extends InsnFormat {
    /** {@code non-null;} unique instance of this class */
    public static final InsnFormat THE_ONE = new Form52c();

    /**
     * Constructs an instance. This class is not publicly
     * instantiable. Use {@link #THE_ONE}.
     */
    private Form52c() {
        // This space intentionally left blank.
    }

    /** {@inheritDoc} */
    @Override
    public String insnArgString(DalvInsn insn) {
        RegisterSpecList regs = insn.getRegisters();
        return regs.get(0).regString() + ", " + regs.get(1).regString() +
            ", " + cstString(insn);
    }

    /** {@inheritDoc} */
    @Override
    public String insnCommentString(DalvInsn insn, boolean noteIndices) {
        if (noteIndices) {
            return cstComment(insn);
        } else {
            return "";
        }
    }

    /** {@inheritDoc} */
    @Override
    public int codeSize() {
        return 5;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCompatible(DalvInsn insn) {
        if (! ALLOW_EXTENDED_OPCODES) {
            return false;
        }

        RegisterSpecList regs = insn.getRegisters();
        if (!((insn instanceof CstInsn ci) &&
              (regs.size() == 2) &&
              unsignedFitsInShort(regs.get(0).getReg()) &&
              unsignedFitsInShort(regs.get(1).getReg()))) {
            return false;
        }

        Constant cst = ci.getConstant();

        return (cst instanceof CstType) ||
            (cst instanceof CstFieldRef);
    }

    /** {@inheritDoc} */
    @Override
    public BitSet compatibleRegs(DalvInsn insn) {
        RegisterSpecList regs = insn.getRegisters();
        BitSet bits = new BitSet(2);

        bits.set(0, unsignedFitsInShort(regs.get(0).getReg()));
        bits.set(1, unsignedFitsInShort(regs.get(1).getReg()));
        return bits;
    }

    /** {@inheritDoc} */
    @Override
    public void writeTo(AnnotatedOutput out, DalvInsn insn) {
        RegisterSpecList regs = insn.getRegisters();
        int cpi = ((CstInsn) insn).getIndex();

        write(out,
                opcodeUnit(insn),
                cpi,
                (short) regs.get(0).getReg(),
                (short) regs.get(1).getReg());
    }
}
