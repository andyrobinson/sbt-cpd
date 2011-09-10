/*
 * This file is part of cpd4sbt.
 * 
 * Copyright (c) 2010, 2011 Joachim Hofer
 * All rights reserved.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.johoop.cpd4sbt

import sbt._
import Keys._
import Project.Initialize

import java.io.File

private[cpd4sbt] trait Settings extends Plugin {
  private val cpdConfig = config("cpd") hide  

  /** Output path for CPD reports. Defaults to <code>target / "cpd"</code>. */
  val cpdTargetPath = SettingKey[File]("cpd-target-path")
  
  val cpd = TaskKey[Unit]("cpd")
  
  protected def cpdAction


  val cpdSettings = Seq(
      ivyConfigurations += cpdConfig,
      libraryDependencies += "pmd" % "pmd" % "4.2.5" % "cpd->default" intransitive(),
      
      cpd := cpdAction,
      
      cpdTargetPath <<= (crossTarget) { _ / "cpd" })
}

//  /** Output path for CPD reports. Defaults to <code>outputPath / "cpd"</code>. */
//  protected val cpdOutputPath: Path
//
//  /** Name of the report file to generate. Defaults to <code>"cpd.xml"</code> */
//  protected lazy val cpdReportName = "cpd.xml"
//
//  /** Paths of the source files to analyze. Defaults to <code>List(mainSourcePath)</code>. */
//  protected val cpdSourcePaths: List[Path]
//
//  /** Maximum amount of memory to allow for CPD (in MB). Defaults to <code>512</code>.*/
//  protected lazy val cpdMaxMemoryInMB = 512
//
//  /** Minimum number of tokens of potential duplicates. Defaults to <code>100</code>.*/
//  protected lazy val cpdMinimumTokens = 100
//
//  /** Source file encoding. Defaults to <code>"utf-8"</code>. */
//  protected lazy val cpdSourceEncoding = "utf-8"
//
//  /** Output file encoding. Defaults to <code>"utf-8"</code>. */
//  protected lazy val cpdOutputEncoding = "utf-8"
//
//  /** Language to analyze. Defaults to <code>Java</code>. 
//    * If you want Scala, extend the CPD tokenizers (or try <code>Any</code>)! */
//  protected lazy val cpdLanguage = CPDLanguage.Java
//
//  /** Type of CPD report. Defaults to <code>XML</code>. */
//  protected lazy val cpdReportType = CPDReportType.XML
