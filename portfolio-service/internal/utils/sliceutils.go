package utils

func Filter[T any](slice []T, f func(T) bool) []T {
	var filtered []T
	for _, v := range slice {
		if f(v) {
			filtered = append(filtered, v)
		}
	}
	return filtered
}

func Reduce[T, R any](slice []T, reducer func(T, R) R, acc R) R {
	for _, v := range slice {
		acc = reducer(v, acc)
	}
	return acc
}
