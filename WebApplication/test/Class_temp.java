 <#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package

?? && package != "">
package ${package};

/**
 * Local project import
 */
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.*;
import adt.node.*;
import adt.interfaces.*;
import csv.converter.*;
import xenum.*;



</#if>
/**
 *
 * @author ${user}
 */
public class $ {

    name
}
{

}
