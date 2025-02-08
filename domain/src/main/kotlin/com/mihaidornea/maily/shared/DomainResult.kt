package com.mihaidornea.maily.shared

sealed class DomainResult<out T>

class Success<out T>(val data: T) : DomainResult<T>() {
    override fun equals(other: Any?): Boolean {
        return if (other is Success<*>) {
            data == other.data
        } else {
            super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return data?.hashCode() ?: 0
    }
}

class Failure<out T>(val throwable: Throwable) : DomainResult<T>() {
    override fun equals(other: Any?): Boolean {
        return if (other is Failure<*>) {
            throwable::class.java == other.throwable::class.java && throwable.message == other.throwable.message
        } else {
            super.equals(other)
        }
    }

    override fun hashCode(): Int {
        return throwable.hashCode()
    }
}

fun <T, U> DomainResult<T>.map(transform: (T) -> U): DomainResult<U> {
    return when (this) {
        is Success -> Success(data = transform(data))
        is Failure -> Failure(throwable = throwable)
    }
}

inline fun <T> DomainResult<T>.onSuccess(block: (T) -> Unit): DomainResult<T> =
    apply {
        if (this is Success<T>) block(data)
    }

inline fun <T> DomainResult<T>.onFailure(block: (Throwable) -> Unit): DomainResult<T> =
    apply {
        if (this is Failure<T>) block(throwable)
    }
