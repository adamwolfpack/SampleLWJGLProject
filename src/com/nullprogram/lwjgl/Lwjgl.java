package com.nullprogram.lwjgl;

import com.nullprogram.guide.Arch;
import static com.nullprogram.guide.NativeGuide.prepare;

/**
 * Prepares all of the LWJGL native binaries for loading.
 */
public final class Lwjgl {

    /** Hidden constructor. */
    private Lwjgl() {
    }

    /**
     * Prepares all of the LWJGL native libraries for loading.
     * @throws java.io.IOException when the libraries could not be loaded
     */
    public static void setup() throws java.io.IOException {
        prepare(Arch.LINUX_64, "/native/linux/libjinput-linux64.so");
        prepare(Arch.LINUX_32, "/native/linux/libjinput-linux.so");
        prepare(Arch.LINUX_64, "/native/linux/liblwjgl64.so");
        prepare(Arch.LINUX_32, "/native/linux/liblwjgl.so");
        prepare(Arch.LINUX_64, "/native/linux/libopenal64.so");
        prepare(Arch.LINUX_32, "/native/linux/libopenal.so");
        prepare(Arch.MAC_64, "/native/macosx/libjinput-osx.jnilib");
        prepare(Arch.MAC_64, "/native/macosx/liblwjgl.jnilib");
        prepare(Arch.MAC_64, "/native/macosx/openal.dylib");
        prepare(Arch.WINDOWS_64, "/native/windows/jinput-dx8_64.dll");
        prepare(Arch.WINDOWS_32, "/native/windows/jinput-dx8.dll");
        prepare(Arch.WINDOWS_64, "/native/windows/jinput-raw_64.dll");
        prepare(Arch.WINDOWS_32, "/native/windows/jinput-raw.dll");
        prepare(Arch.WINDOWS_64, "/native/windows/lwjgl64.dll");
        prepare(Arch.WINDOWS_32, "/native/windows/lwjgl.dll");
        prepare(Arch.WINDOWS_32, "/native/windows/OpenAL32.dll");
        prepare(Arch.WINDOWS_64, "/native/windows/OpenAL64.dll");
    }
}
