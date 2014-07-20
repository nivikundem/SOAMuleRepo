<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
<xsl:output method="text" omit-xml-declaration="yes" indent="no" encoding="ISO-8859-1" />
<xsl:template match="/">
<xsl:for-each select="//report[@type='CROSSING ACTIVITY']/reportDetails">
       <xsl:value-of select="concat(gateNumber,',',vehicleType,',',direction,',',vrn,',',crossingDateAndTime,'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>