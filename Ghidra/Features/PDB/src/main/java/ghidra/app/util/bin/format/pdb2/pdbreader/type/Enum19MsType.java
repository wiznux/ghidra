/* ###
 * IP: GHIDRA
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
package ghidra.app.util.bin.format.pdb2.pdbreader.type;

import ghidra.app.util.bin.format.pdb2.pdbreader.*;

/*
 * TODO this record type is a guess, using only data (no documentation) in its crafting.
 * Thus, it can be cleaned up using trusted, msft-originated documentation if it becomes
 * available and/or using more data analysis and/or non-trusted documentation.
 * We believe there must be a union, enum, and interface (stubbed only guesses at PDB_IDs and can
 * be incorrectly assigned) that are similar to the 0x1608 and 0x1609 class and struct classes,
 * and yet similar to their older versions of union, enum, and interface as well.
 */
/**
 * This class represents the VS19 <B>MsType</B> flavor of Enum type. (At this time,
 * PDB_ID is guessed to be an enum similar to {@link EnumMsType}, eliminating
 * the redundant count field, and adding other unknown field parsing.)
 * <P>
 * Note: we do not necessarily understand each of these data type classes.  Refer to the
 *  base class for more information.
 */
public class Enum19MsType extends AbstractEnumMsType {

	public static final int PDB_ID = 0x160b;

	/**
	 * Constructor for this type.
	 * @param pdb {@link AbstractPdb} to which this type belongs.
	 * @param reader {@link PdbByteReader} from which this type is deserialized.
	 * @throws PdbException upon error parsing a field.
	 */
	public Enum19MsType(AbstractPdb pdb, PdbByteReader reader) throws PdbException {
		super(pdb, reader);
		property = new MsProperty(reader);
		int x = reader.parseUnsignedShortVal(); //unknown
		underlyingRecordNumber = RecordNumber.parse(pdb, reader, RecordCategory.TYPE, 32);
		fieldDescriptorListRecordNumber = RecordNumber.parse(pdb, reader, RecordCategory.TYPE, 32);
		//TODO: has more... guessing below... commented out some other conditions, but we
		// might want to investigate if any data hits them.
		if (reader.hasMoreNonPad()) {
			name = reader.parseString(pdb, StringParseType.StringNt);
			if (reader.hasMoreNonPad()) {
				// Additional parsing
				mangledName = reader.parseString(pdb, StringParseType.StringNt);
			}
//			else if (reader.hasMore()) {
//			}
		}
//		else {
//		}
		reader.skipPadding();
	}

	@Override
	public int getPdbId() {
		return PDB_ID;
	}

}
