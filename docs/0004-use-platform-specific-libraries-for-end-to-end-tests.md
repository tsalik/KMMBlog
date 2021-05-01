# 4. Use platform specific libraries for end-to-end tests

Date: 2021-05-01

## Status

Accepted

## Context

GOOS style TDD begins every feature with a failing end-to-end test and the very first feature needs a "Walking Skeleton" to showcase that the test harness can work with external libraries and each framework. Since UI is different platform end-to-end tests that should pass through UI should be developed independently as well.

## Decision

Each platform will have its own end-to-end tests, developed independently with the choice of libraries made by the platform developer.

## Consequences

Even though this may seem wasteful, since the apps will not be treated as black boxes every developer can use the libraries their platform supports and apply the testing style of their choice. Eventually, this will make the tests easier to write, maintain and diagnose.
