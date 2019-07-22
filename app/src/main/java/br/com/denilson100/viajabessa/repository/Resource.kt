package br.com.denilson100.viajabessa.repository

class Resource<T>(
val data: T?,
val error: String? = null
)

fun <T> resourceFail(
    resource: Resource<T?>?,
    error: String?
): Resource<T?> {
    if (resource != null) {
        return Resource(data = resource.data, error = error)
    }
    return Resource(data = null, error = error)
}