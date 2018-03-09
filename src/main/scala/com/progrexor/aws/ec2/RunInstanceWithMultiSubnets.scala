package com.progrexor.aws.ec2

import com.amazonaws.services.ec2.AmazonEC2ClientBuilder
import com.amazonaws.services.ec2.model.{InstanceNetworkInterfaceSpecification, InstanceType, RunInstancesRequest}

object RunInstanceWithMultiSubnets {

  def main(args: Array[String]): Unit = {

    // Specify network interface 1
    val instanceNetworkInterfaceSpecification1 = new InstanceNetworkInterfaceSpecification()
      .withDeviceIndex(0)
      .withNetworkInterfaceId("eth0")
      .withSubnetId("subnet01")
      .withPrivateIpAddress("1.2.3.4") // withPrivateIpAddresses for multiple private IPs

    // Specify network interface 2
    val instanceNetworkInterfaceSpecification2 = new InstanceNetworkInterfaceSpecification()
      .withDeviceIndex(1)
      .withNetworkInterfaceId("eth1")
      .withSubnetId("subnet02")
      .withPrivateIpAddress("2.3.4.5") // withPrivateIpAddresses for multiple private IPs

    // Define EC2 run request with two interfaces
    val runInstancesRequest = new RunInstancesRequest()
      .withImageId("f34f4fj5rthgdsf3f343f34f")
      .withInstanceType(InstanceType.M4Xlarge)
      .withNetworkInterfaces(
        instanceNetworkInterfaceSpecification1,
        instanceNetworkInterfaceSpecification2
      )

    // Create EC2 client
    val ec2 = AmazonEC2ClientBuilder.defaultClient();

    // Run EC2 instance with two interfaces
    val runResponse = ec2.runInstances(runInstancesRequest)

  }

}
