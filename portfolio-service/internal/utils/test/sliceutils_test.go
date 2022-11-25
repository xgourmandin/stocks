package test

import (
	"portfolio-service/internal/utils"
	"testing"
)

func TestBasicStringFilter(t *testing.T) {
	slice := []string{"a", "b", "c"}
	filtered := utils.Filter(slice, func(s string) bool { return s == "b" })
	if len(filtered) != 1 {
		t.Errorf("Filtered slice should have %d element(s), %d found", 1, len(filtered))
	}
}

func TestEvenNumberFilter(t *testing.T) {
	slice := []int{1, 2, 3, 4, 5, 6}
	filtered := utils.Filter(slice, func(s int) bool { return s%2 == 0 })
	if len(filtered) != 3 {
		t.Errorf("Filtered slice should have %d element(s), %d found", 3, len(filtered))
	}
}

func TestReduce(t *testing.T) {
	slice := []int{1, 2, 3, 4, 5}
	sum := utils.Reduce(slice, func(v int, acc int) int { return v + acc }, 0)
	if sum != 15 {
		t.Errorf("Reduced value for slice should have been %d, %d found", 3, sum)
	}
}
