# 2. Implement apps using KMM

Date: 2021-04-26

## Status

Accepted

## Context

KMM shows a lot of promise and investigating its usage on a production app - however small - will flesh out its potential and drawbacks as well. I will use my personal blog, which is build with Hugo and create custom `json` outputs to serve the apps.

## Decision

Android and iOS apps for the blog will use Kotlin MultiPlatform Mobile(KMM) to share the domain logic and each platform will use its own UI. Additionally, KMM libraries will be investigated in order to share the same code for HTTP.

## Consequences

At the time of writing, KMM is still in Alpha stability level. This means that it's highly possible there will be compatibility issues during development. Since the whole project is the investigation of KMM, this is anticipated and well-known.
