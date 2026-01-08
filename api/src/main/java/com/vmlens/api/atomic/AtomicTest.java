package com.vmlens.api.atomic;

/**
 * Tests if a class has atomic methods
 * Can be locked based, no check for deadlock
 * methods are either
 *      independent
 *      commutative
 *      or not commutative
 * currently only independent and other is used
 *
 */
public interface AtomicTest {

    void runTests();

}
