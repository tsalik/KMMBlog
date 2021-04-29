# 3. Use GOOS TDD style for development

Date: 2021-04-29

## Status

Accepted

## Context

Although I've studied and used the example on GOOS(Growing Object-Oriented Software, Guided by Tests - Steve Freeman, Nat Pryce), I haven't used acceptance on a greenfield project(but I have to a certain degree on brownfield projects on production). I would like to investigate how the use of acceptance tests affects development on a brand new project that will eventually go live.

## Decision

The development will be based on GOOS style TDD, using acceptance tests when adding a feature and then TDD(with mocks if needed, as in the book). A walking skeleton will be built at the start and the features will be added as incrementally as possible.

## Consequences

The project already investigates a technology that is in Alpha so this adds an extra layer of uncertainty - how easy is it to test end-to-end something that is so unstable and can you deduct safe takeaway about a development methodology on what is essentially RnD? On the other hand, if TDD can work on such a task, then it can anywhere.
