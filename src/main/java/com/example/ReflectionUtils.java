import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static boolean isAccessible(AccessibleObject accessibleObject) {
        boolean accessible = accessibleObject.isAccessible(); // Use of deprecated isAccessible method
        accessibleObject.setAccessible(true);
        return accessible;
    }

    public static boolean isFieldAccessible(Field field) {
        return isAccessible(field);
    }

    public static boolean isMethodAccessible(Method method) {
        return isAccessible(method);
    }
}