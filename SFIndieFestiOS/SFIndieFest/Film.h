//
//  Film.h
//  SFIndieFest
//
//  Created by Eduardo Quiroz on 9/24/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Film : NSObject
@property (nonatomic, retain)  NSString * filmTitle;
@property (nonatomic, retain)  NSString * filmImageUrl;
@property (nonatomic, retain)  NSString * dateTime;
@property (nonatomic, retain)  NSString * synopsis;
@property (nonatomic, retain)  NSString * trailerUrl;
@property (nonatomic, retain)  NSString * filmUid;

-(NSString *)getPurchaseUrl;
+(NSArray *)getStaticFilms;
@end
