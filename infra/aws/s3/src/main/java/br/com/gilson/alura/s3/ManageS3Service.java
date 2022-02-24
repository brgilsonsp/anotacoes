package br.com.gilson.alura.s3;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class ManageS3Service {
	
	final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private static S3Client buildS3Client() {
		final String clientId = "AKIAWNRC3J25FWWUA46H";
		final String clientSecret = "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";
		
		final AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(clientId, clientSecret);
		final StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);
		return S3Client.builder().credentialsProvider(credentialsProvider).region(Region.US_EAST_1).build();
	}
	
	public static void main(String[] args) {
		
		final String bucketName = "gilson.teste.na.conta.da.sheila.one";
		final String objectName = "one_file.txt";
		
		final S3Client s3Client = buildS3Client();
		ManageS3Service manageS3Service = new ManageS3Service();
		
		manageS3Service.createBucket(bucketName, s3Client);
		System.out.println();
		manageS3Service.listBuckets(s3Client);
		System.out.println();
		
		manageS3Service.sendObjectToBucket(bucketName, s3Client, objectName);
		System.out.println();
		
		manageS3Service.listObjectsByBucketName(bucketName, s3Client);
		System.out.println();
		
		manageS3Service.getObjectToBucket(bucketName, s3Client, objectName);
		System.out.println();
		
		manageS3Service.cleanBucket(bucketName, s3Client, objectName);
		System.out.println();
		
		manageS3Service.listObjectsByBucketName(bucketName, s3Client);
		System.out.println();
		
		manageS3Service.deleteBcuketByName(bucketName, s3Client);
		System.out.println();
		manageS3Service.listBuckets(s3Client);
		System.out.println();
		
	}
	
	private void createBucket(final String bucketName, final S3Client s3Client) {
		System.out.println("Creating bucket....");
		CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(bucketName).build();
		CreateBucketResponse createBucketResponse = s3Client.createBucket(createBucketRequest);
		System.out.println("Bucket created: " + createBucketResponse.toString());
		
	}
	
	private void deleteBcuketByName(final String bucketName, final S3Client s3Client) {
		System.out.println("Deleting bucket....");
		DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder().bucket(bucketName).build();
		DeleteBucketResponse deleteBucketResponse = s3Client.deleteBucket(deleteBucketRequest);
		System.out.println("Bucket deleted: " + deleteBucketResponse.sdkHttpResponse().statusCode());
		
	}
	
	private void listBuckets(final S3Client s3Client) {
		System.out.println("Listing buckets....");
		if(s3Client.listBuckets().hasBuckets()) {
			
			s3Client.listBuckets().buckets().forEach(i -> System.out.println(String.format("%s - %s", i.name(), DATA_FORMAT.format(Date.from(i.creationDate())))));
		}else {
			System.out.println("No bucket found!");
		}
		System.out.println("Finished list buckets....");
		
	}
	
	private void sendObjectToBucket(final String bucketName, final S3Client s3Client, final String objectName) {
		System.out.println("Seding object to buckets....");
		final PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(objectName).build();
		final Path path = Path.of("cli.txt");
		PutObjectResponse objectResponse = s3Client.putObject(putObjectRequest, path);
		System.out.println(String.format("Object $s sent to buckets....", objectResponse.eTag()));
		
	}
	
	private void listObjectsByBucketName(final String bucketName, final S3Client s3Client) {
		System.out.println("Listing objects from buckets....");
		final ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder().bucket(bucketName).build();
		ListObjectsResponse listObjectsResponse = s3Client.listObjects(listObjectsRequest);
		if(listObjectsResponse.hasContents()){
			listObjectsResponse.contents().
			forEach(i -> System.out.println(
					String.format("%s - %s - %s - %s", i.key(), 
							i.owner().displayName(), 
							DATA_FORMAT.format(Date.from(i.lastModified())),
							i.size())));
		}else {
			System.out.println(String.format("No object found to bucket %s!", bucketName));
		}
		System.out.println("Finished list buckets....");
	}
	
	private void getObjectToBucket(final String bucketName, final S3Client s3Client, final String objectName) {
		System.out.println("Getting object from buckets....");
		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(objectName).build();
		final Path path = Path.of(objectName);
		GetObjectResponse objectResponse = s3Client.getObject(getObjectRequest, path);
		
		System.out.println(String.format("Download finished of the file %s....", objectResponse.eTag()));
		
	}
	
	private void cleanBucket(final String bucketName, final S3Client s3Client, final String objectName) {
		System.out.println("Clean up buckets....");
		DeleteObjectRequest objectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(objectName).build();
		DeleteObjectResponse objectResponse = s3Client.deleteObject(objectRequest);

		System.out.println(String.format("Buckets clean? %s", objectResponse.sdkHttpResponse().statusCode()));
	}

}
