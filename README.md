# Software Testing Project – SmartTube

This project showcases the practical application of core software testing techniques using the **SmartTube open-source Android application**. As part of COE891 (Software Testing and QA), we applied four major testing strategies to evaluate and improve code quality in a production-level Java codebase.

> **Tested System:** [SmartTube by yuliskov](https://github.com/yuliskov/SmartTube)  
> **Team Project (W2025) – Toronto Metropolitan University**

---

## Project Overview

We analyzed and tested 10 methods across 5 Java classes using:

- **Input Space Partitioning (ISP)**
- **Graph-Based Testing (CFG & DFG)**
- **Logic-Based Testing (GACC, CACC, RACC)**
- **Mutation Testing (with PIT and JUnit)**

These techniques helped us model different code paths, input behaviors, data flows, and decision points—ensuring comprehensive test coverage.

---

## Tools & Technologies

- **Java 8**
- **JUnit 4**
- **Eclipse IDE**
- **PIT (Pitest) Mutation Testing Plugin**
- **Control Flow & Data Flow Graphs (CFG/DFG)**  
- **SmartTube Source Code** ([GitHub Repo](https://github.com/yuliskov/SmartTube))

---

## Key Testing Examples

### 🔸 `CopyOnWriteHashList.java`
- Tested `add(T item)` and `add(int index, T item)`
- Used **Input Space Partitioning**, CFG/DFG, and mutation testing
- Found unhandled edge cases (e.g. null items, duplicates)

### 🔸 `Proxy.java`
- Tested constructor logic and equality checks
- Revealed fault in null type handling via ISP

### 🔸 `PasswdURI.java`
- Tested URI parsing for malformed inputs
- Identified inconsistencies with incomplete URIs

### 🔸 `DateFormatter.java` & `ActionHelpers.java`
- Evaluated date comparison and UI color logic
- Built test cases to ensure decision logic is sound

---

## Mutation Testing Summary

- **Total Methods Tested:** 10  
- **Average Mutation Kill Rate:** 73–79%  
- **Line Coverage:** 100% for key methods  
- Surviving mutants highlighted gaps in test granularity, offering insight for further improvement.

---

## Report

The full project report (with test cases, CFG/DFG diagrams, logic tables, and findings) is available in this repo as:  
📎 `COE891_Final_Report.pdf`

---

## What We Learned

- How to design high-quality unit tests using formal methods  
- The strengths and limitations of logic coverage vs. mutation testing  
- The challenges of testing real-world Android applications  
- How to identify meaningful faults, not just boost coverage metrics

---

## Team Members

- Maria Labeeba  
- Eeman Asif  
- Sabia Bhuiyan  
- Husain Yunus Maudiwala  
- Ariba Mirza



