# ADR-0001: Use a monorepo

**Status:** Accepted

## Context
LinkPulse has several backend services, a frontend, and shared infrastructure
config. It is built and maintained by one developer.

## Decision
Keep all code in a single Git repository, with one folder per service under
`services/`, plus `frontend/` and `infra/`.

## Consequences
- Easy to change a service and its event contract in one commit
- One CI pipeline and one place to browse for reviewers
- Services must stay independently buildable and deployable
- In a larger team, separate repos per service might be better