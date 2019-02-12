package com.kayo.lib.tack.api.utils;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class BundleUtil {

    public static void mapToBundle(@NonNull Bundle bundle, @NonNull Map<String, Object> map) {
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            objectToBundle(bundle, key, value);
        }
    }

    public static void bundleToBundle(@NonNull Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle2 == null) {
            return;
        }
        bundle.putAll(bundle2);
    }

    public static void objectToBundle(@NonNull Bundle bundle, String key, Object value) {
        if (value instanceof Parcelable[]) {
            putToBundle(bundle, key, (Parcelable[]) value);
        } else if (value instanceof Byte[]) {
            putToBundle(bundle, key, (Byte[]) value);
        } else if (value instanceof short[]) {
            putToBundle(bundle, key, (short[]) value);
        } else if (value instanceof char[]) {
            putToBundle(bundle, key, (char[]) value);
        } else if (value instanceof float[]) {
            putToBundle(bundle, key, (float[]) value);
        } else if (value instanceof CharSequence[]) {
            putToBundle(bundle, key, (CharSequence[]) value);
        } else if (value instanceof Byte) {
            putToBundle(bundle, key, (byte) value);
        } else if (value instanceof Short) {
            putToBundle(bundle, key, (short) value);
        } else if (value instanceof Character) {
            putToBundle(bundle, key, (char) value);
        } else if (value instanceof String) {
            putToBundle(bundle, key, (String) value);
        } else if (value instanceof Integer) {
            putToBundle(bundle, key, (int) value);
        } else if (value instanceof Float) {
            putToBundle(bundle, key, (float) value);
        } else if (value instanceof Double) {
            putToBundle(bundle, key, (double) value);
        } else if (value instanceof Long) {
            putToBundle(bundle, key, (long) value);
        } else if (value instanceof Parcelable) {
            putToBundle(bundle, key, (Parcelable) value);
        } else if (value instanceof Serializable) {
            putToBundle(bundle, key, (Serializable) value);
        } else if (value instanceof CharSequence) {
            putToBundle(bundle, key, (CharSequence) value);
        } else if (value instanceof Size) {
            putToBundle(bundle, key, (Size) value);
        } else if (value instanceof SizeF) {
            putToBundle(bundle, key, (SizeF) value);
        } else if (value instanceof Bundle) {
            putToBundle(bundle, key, (Bundle) value);
        } else if (value instanceof IBinder) {
            putToBundle(bundle, key, (IBinder) value);
        } else if (value instanceof SparseArray) {
            SparseArray temp = (SparseArray) value;
            if (temp.size() > 0) {
                Object o = temp.get(0);
                if (o instanceof Parcelable) {
                    putSparseParcelableArrayToBundle(bundle, key, (SparseArray) value);
                } else {
                    putSparseParcelableArrayToBundle(bundle, key, null);
                }
            } else {
                putSparseParcelableArrayToBundle(bundle, key, null);
            }

        } else if (value instanceof ArrayList) {
            ArrayList temp = (ArrayList) value;
            if (!temp.isEmpty()) {
                Object o = temp.get(0);
                if (o instanceof String) {
                    putStringArrayListToBundle(bundle, key, temp);
                } else if (o instanceof Integer) {
                    putIntegerArrayListToBundle(bundle, key, temp);
                } else if (o instanceof CharSequence) {
                    putCharSequenceArrayListToBundle(bundle, key, temp);
                } else if (o instanceof Parcelable) {
                    putParcelableArrayListToBundle(bundle, key, temp);
                }
            }
        }
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, byte value) {
        bundle.putByte(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, char value) {
        bundle.putChar(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, short value) {
        bundle.putShort(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, int value) {
        bundle.putInt(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, float value) {
        bundle.putFloat(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, double value) {
        bundle.putDouble(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, long value) {
        bundle.putLong(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, String value) {
        bundle.putString(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, Parcelable value) {
        bundle.putParcelable(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, Serializable value) {
        bundle.putSerializable(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, CharSequence value) {
        bundle.putCharSequence(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, Size value) {
        bundle.putSize(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, SizeF value) {
        bundle.putSizeF(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, Parcelable[] value) {
        bundle.putParcelableArray(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable CharSequence[] value) {
        bundle.putCharSequenceArray(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable byte[] value) {
        bundle.putByteArray(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable short[] value) {
        bundle.putShortArray(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable char[] value) {
        bundle.putCharArray(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable float[] value) {
        bundle.putFloatArray(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable Bundle value) {
        bundle.putBundle(key, value);
    }

    public static void putToBundle(@NonNull Bundle bundle, String key, @Nullable IBinder value) {
        bundle.putBinder(key, value);
    }

    public static void putSparseParcelableArrayToBundle(@NonNull Bundle bundle, String key, @Nullable SparseArray<? extends Parcelable> value) {
        bundle.putSparseParcelableArray(key, value);
    }

    public static void putParcelableArrayListToBundle(@NonNull Bundle bundle, String key, @Nullable ArrayList<? extends Parcelable> value) {
        bundle.putParcelableArrayList(key, value);
    }

    public static void putIntegerArrayListToBundle(@NonNull Bundle bundle, String key, @Nullable ArrayList<Integer> value) {
        bundle.putIntegerArrayList(key, value);
    }

    public static void putStringArrayListToBundle(@NonNull Bundle bundle, String key, @Nullable ArrayList<String> value) {
        bundle.putStringArrayList(key, value);
    }

    public static void putCharSequenceArrayListToBundle(@NonNull Bundle bundle, String key, @Nullable ArrayList<CharSequence> value) {
        bundle.putCharSequenceArrayList(key, value);
    }


}
