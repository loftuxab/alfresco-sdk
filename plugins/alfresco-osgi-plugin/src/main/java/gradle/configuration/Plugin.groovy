package gradle.configuration

/**
 * Created by bhagyasilva on 17/04/15.
 */
interface Plugin<T> {

    public void apply(T t);
}
