package dreamdiary

import org.mockito.ArgumentCaptor
import org.mockito.Mockito


fun <T>ArgumentCaptor<T>.argsCapture() {
    this.capture()
}

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

fun <T> any(): T {
    Mockito.any<T>()
    return null as T
}
